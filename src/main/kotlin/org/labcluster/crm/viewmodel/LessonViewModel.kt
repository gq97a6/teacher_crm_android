package org.labcluster.crm.viewmodel


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.labcluster.crm.shared.Mock
import org.labcluster.crm.shared.model.Course
import org.labcluster.crm.shared.model.Lesson
import org.labcluster.crm.shared.model.Student
import org.labcluster.crm.shared.model.Topic

class LessonViewModel : ViewModel() {

    val lesson = MutableStateFlow(Mock.lessons[0])
    val students = MutableStateFlow(listOf<Student>())
    val hasBegun = MutableStateFlow(false)
    val course = MutableStateFlow(Course())
    val topics = MutableStateFlow(listOf<Topic>())
    val topic = MutableStateFlow("")
    val isMenuExpanded = MutableStateFlow(false)

    //.stateIn(
    //    scope = viewModelScope,
    //    started = SharingStarted.WhileSubscribed(5000),
    //    initialValue = false
    //)

    init {
        /*
        val randomLesson = db.lessonQueries
            .selectAll()
            .executeAsList()
            .map { it.toModel(db) }
            .random()
         */

        students.value = lesson.value.attendees
        course.value = lesson.value.course ?: Course()
        topics.value = lesson.value.course?.topics ?: listOf()
        topic.value = topics.value.randomOrNull()?.name ?: ""

    }

    fun onMenuClicked() {
        isMenuExpanded.value = !isMenuExpanded.value
    }

    fun onSetTopic(value: String) {
        topic.value = value
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