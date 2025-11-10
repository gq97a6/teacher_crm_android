package org.labcluster.crm.viewmodels


import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.labcluster.crm.app.App
import org.labcluster.crm.app.AppState
import org.labcluster.crm.shared.model.Course
import org.labcluster.crm.shared.model.Lesson
import org.labcluster.crm.shared.model.Student
import org.labcluster.crm.shared.model.Topic

class LessonViewModel(
    app: Application = App.app,
    state: AppState = App.state
) : ViewModel() {

    val students = MutableStateFlow(listOf<Student>())

    val lesson = MutableStateFlow(Lesson())
    val hasBegun = lesson.map { it.epochBegin != null }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    val course = MutableStateFlow(Course("", listOf()))
    val topics: StateFlow<List<Topic>> = course.map { it.topics }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val topic = MutableStateFlow("")
    val isMenuExpanded = MutableStateFlow(false)

    fun onMenuClicked() {
        isMenuExpanded.value = !isMenuExpanded.value
    }

    fun onSetTopic(value: String) {
        topic.value = value
    }

    fun onDropdownMenuDismissed() {
        isMenuExpanded.value = false
    }
}