package org.labcluster.crm.viewmodel


import androidx.compose.ui.state.ToggleableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import org.labcluster.crm.app.App
import org.labcluster.crm.app.AppState
import kotlin.time.Clock

class LessonViewModel(val state: AppState = App.state) : ViewModel() {

    val attendance: StateFlow<List<ToggleableState>> = state.lesson.map { lesson ->
        lesson.students.map { student ->
            ToggleableState(student.uuid in lesson.attendance)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val isEditable = MutableStateFlow(false)
    val isMenuExpanded = MutableStateFlow(false)

    fun onMenuClicked() {
        isMenuExpanded.value = !isMenuExpanded.value
    }

    fun onStudentCheckbox(index: Int) {
        state.alter {
            lesson.update {
                val student = it.students[index]

                val attendance = it.attendance

                val newList = if (student.uuid !in it.attendance) it.attendance + student.uuid
                else it.attendance.filter { uuid -> uuid != student.uuid }

                it.copy(attendance = newList)
            }
        }

        //attendance.update { current ->
        //    current.mapIndexed { i, item ->
        //        if (i == index) ToggleableState(item == ToggleableState.Off)
        //        else item
        //    }
        //}
    }

    fun onDropdownMenuDismissed() {
        isMenuExpanded.value = false
    }

    fun onEditLesson() {
        isMenuExpanded.value = false
    }

    fun onShowTopic() {
        isMenuExpanded.value = false
    }

    fun onShowCourse() {
        isMenuExpanded.value = false
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
            lesson.value = lesson.value.copy(epochBegin = null)
        }
    }

    fun onConfirmClicked() {
        state.alter {
            lesson.value = lesson.value.copy(epochBegin = Clock.System.now().epochSeconds)
            isEditable.value = false
        }
    }
}