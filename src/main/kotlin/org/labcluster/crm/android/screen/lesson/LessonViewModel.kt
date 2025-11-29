package org.labcluster.crm.android.screen.lesson

import android.content.ClipData
import android.widget.Toast
import androidx.compose.ui.platform.ClipEntry
import androidx.compose.ui.platform.Clipboard
import androidx.compose.ui.state.ToggleableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.labcluster.crm.android.TopicViewKey
import org.labcluster.crm.android.app.App
import org.labcluster.crm.android.app.AppApi
import org.labcluster.crm.android.app.AppState
import org.labcluster.crm.android.storage.Storage.deepCopy
import org.labcluster.crm.shared.Open
import org.labcluster.crm.shared.model.Lesson
import kotlin.time.Clock

@Open
class LessonViewModel(
    val state: AppState = App.state,
    val api: AppApi = App.api,
    val app: App = App.app
) : ViewModel() {

    val isEditable = MutableStateFlow(false)
    val isLoading = MutableStateFlow(false)
    val clock = state.chronos.clock(viewModelScope)
    val editLesson = MutableStateFlow(Lesson())

    //Display editable or real lesson based on isEditable
    val displayedLesson: StateFlow<Lesson> = combine(
        isEditable,
        editLesson,
        state.lesson.lesson
    ) { isEditable, editLesson, theLesson ->
        if (isEditable) editLesson
        else theLesson
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Lesson()
    )

    //Map attendance list derived from display lesson
    val attendance: StateFlow<List<ToggleableState>> = displayedLesson.map { lesson ->
        lesson.students.map { student ->
            ToggleableState(student.uuid in lesson.attendance)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun onStudentCheckbox(index: Int) {
        state.alter {
            val lesson = editLesson.value
            val student = lesson.students[index]

            //Build new attendance list
            val newList = if (student.uuid !in lesson.attendance) lesson.attendance + student.uuid
            else lesson.attendance.filter { uuid -> uuid != student.uuid }

            //Create new lesson
            val newLesson = lesson.deepCopy() ?: return@alter
            newLesson.attendance = newList.toMutableList()

            //Apply change
            editLesson.value = newLesson
        }
    }

    fun onShowTopic() {
        state.alter {
            state.lesson.lesson.value.topic?.let {
                state.topic.setTopic(it)
                backstack.value.add(TopicViewKey())
            }
        }
    }

    fun onCopyUuid(clipboard: Clipboard) {
        viewModelScope.launch {
            val data = ClipData.newPlainText("uuid", state.lesson.lesson.value.uuid)
            val entry = ClipEntry(data)
            clipboard.setClipEntry(entry)
        }
    }

    fun onShowCourse() {
    }

    fun onStartClicked() {
        isEditable.value = true
        editLesson.value = state.lesson.lesson.value.deepCopy() ?: Lesson()
    }

    fun onEditClicked() {
        isEditable.value = true
        editLesson.value = state.lesson.lesson.value.deepCopy() ?: Lesson()
    }

    fun onCancelClicked() {
        isEditable.value = false
    }

    fun onConfirmClicked() {
        state.alter(viewModelScope) {
            isLoading.value = true
            val timerJob = viewModelScope.launch { delay(1000) }

            val uncommitedLesson = editLesson.value.deepCopy() ?: return@alter
            uncommitedLesson.epochBegin = Clock.System.now().epochSeconds

            val hasBeenCommited = api.putLesson(uncommitedLesson)
            if (hasBeenCommited) lesson.setLesson(uncommitedLesson)
            else Toast.makeText(app, "Wystąpił błąd", Toast.LENGTH_LONG).show()

            timerJob.join()

            isEditable.value = !hasBeenCommited
            isLoading.value = false
        }
    }
}