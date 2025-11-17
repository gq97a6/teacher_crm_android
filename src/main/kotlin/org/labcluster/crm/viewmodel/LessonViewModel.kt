package org.labcluster.crm.viewmodel


import android.widget.Toast
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
    val clock = state.chronos.clock(viewModelScope)

    fun onStudentCheckbox(index: Int) {
        state.alter {
            lesson.update {
                val student = it.students[index]

                val newList = if (student.uuid !in it.attendance) it.attendance + student.uuid
                else it.attendance.filter { uuid -> uuid != student.uuid }

                it.copy(attendance = newList)
            }
        }
    }

    fun onShowTopic() {
        Toast.makeText(
            App.app.baseContext,
            state.lesson.value.topic?.name,
            Toast.LENGTH_SHORT
        ).show()
    }

    fun onShowCourse() {
        Toast.makeText(
            App.app.baseContext,
            state.lesson.value.course?.name,
            Toast.LENGTH_SHORT
        ).show()
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
            lesson.value = lesson.value.copy(
                epochBegin = null,
                attendance = listOf()
            )
        }
    }

    fun onConfirmClicked() {
        state.alter {
            lesson.value = lesson.value.copy(epochBegin = Clock.System.now().epochSeconds)
            isEditable.value = false
        }
    }
}