package org.labcluster.crm.android.screen.lesson

import android.content.ClipData
import androidx.compose.ui.platform.ClipEntry
import androidx.compose.ui.platform.Clipboard
import androidx.compose.ui.state.ToggleableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.labcluster.crm.android.Open
import org.labcluster.crm.android.TopicViewKey
import org.labcluster.crm.android.app.App
import org.labcluster.crm.android.app.AppApi
import org.labcluster.crm.android.app.AppState
import org.labcluster.crm.android.storage.Storage.deepCopy
import kotlin.time.Clock

@Open
class LessonViewModel(
    val state: AppState = App.state,
    val api: AppApi = App.api
) : ViewModel() {

    val attendance: StateFlow<List<ToggleableState>> = state.lesson.lesson.map { lesson ->
        lesson.students.map { student ->
            ToggleableState(student.uuid in lesson.attendance)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val isEditable = MutableStateFlow(false)
    val isLoading = MutableStateFlow(false)
    val clock = state.chronos.clock(viewModelScope)

    fun onStudentCheckbox(index: Int) {
        state.alter {
            val lesson = lesson.lesson.value
            val student = lesson.students[index]

            //Build new attendance list
            val newList = if (student.uuid !in lesson.attendance) lesson.attendance + student.uuid
            else lesson.attendance.filter { uuid -> uuid != student.uuid }

            //Create new lesson
            val newLesson = lesson.deepCopy() ?: return@alter
            newLesson.attendance = newList.toMutableList()

            //Apply change
            this.lesson.setLesson(newLesson)
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
    }

    fun onEditClicked() {
        isEditable.value = true
    }

    fun onCancelClicked() {
        isEditable.value = false
        state.alter {
            val newLesson = lesson.lesson.value.deepCopy() ?: return@alter
            newLesson.epochBegin = null
            newLesson.attendance = mutableListOf()
            lesson.setLesson(newLesson)
        }
    }

    fun onConfirmClicked() {
        state.alter(viewModelScope) {
            isLoading.value = true
            val timerJob = viewModelScope.launch { delay(1000) }

            val newLesson = lesson.lesson.value.deepCopy() ?: return@alter
            newLesson.epochBegin = Clock.System.now().epochSeconds
            val isSuccessful = api.putLesson(newLesson)

            if (isSuccessful) lesson.setLesson(newLesson)

            timerJob.join()
            isEditable.value = false
            isLoading.value = false
        }
    }
}