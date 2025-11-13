package org.labcluster.crm.viewmodel


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.labcluster.crm.app.App
import org.labcluster.crm.app.AppState

class LessonViewModel(val state: AppState = App.state) : ViewModel() {

    val isMenuExpanded = MutableStateFlow(false)

    //.stateIn(
    //    scope = viewModelScope,
    //    started = SharingStarted.WhileSubscribed(5000),
    //    initialValue = false
    //)

    fun onMenuClicked() {
        isMenuExpanded.value = !isMenuExpanded.value
    }

    fun onSetTopic(value: String) {
        state.alter {
            val topic = lesson.value.course?.topics?.find { it.name == value }
            if (topic != null) lesson.value = lesson.value.copy(topic = topic)
        }
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
}