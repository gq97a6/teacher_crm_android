package org.labcluster.crm.viewmodel


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.labcluster.crm.shared.model.Course
import org.labcluster.crm.shared.model.Lesson
import org.labcluster.crm.shared.model.Student
import org.labcluster.crm.shared.model.Topic

class LessonViewModel : ViewModel() {

    val lesson = MutableStateFlow(Lesson())

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

        lesson.value = randomLesson
        students.value = randomLesson.attendees
        course.value = randomLesson.course ?: Course()
        topics.value = randomLesson.course?.topics ?: listOf()
        topic.value = topics.value.randomOrNull()?.name ?: ""
         */
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
}