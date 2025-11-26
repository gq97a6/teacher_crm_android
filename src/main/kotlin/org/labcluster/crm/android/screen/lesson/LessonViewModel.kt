package org.labcluster.crm.android.screen.lesson

import androidx.compose.ui.state.ToggleableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.labcluster.crm.android.Open
import org.labcluster.crm.android.Storage.deepCopy
import org.labcluster.crm.android.app.App
import org.labcluster.crm.android.app.AppState
import kotlin.time.Clock

@Open
class LessonViewModel(val state: AppState = App.state) : ViewModel() {

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
        state.alter {
            val newLesson = lesson.lesson.value.deepCopy() ?: return@alter
            newLesson.epochBegin = Clock.System.now().epochSeconds
            lesson.setLesson(newLesson)

            isEditable.value = false
            //save lessons to local and push to remote
        }
    }
}