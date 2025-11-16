package org.labcluster.crm.app

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.datetime.TimeZone
import org.labcluster.crm.shared.model.Course
import org.labcluster.crm.shared.model.Group
import org.labcluster.crm.shared.model.Lesson
import org.labcluster.crm.shared.model.Student
import org.labcluster.crm.shared.model.Teacher
import org.labcluster.crm.shared.model.Topic

class AppState {
    val timeZone = MutableStateFlow(TimeZone.currentSystemDefault())

    val topic = MutableStateFlow(Topic())
    val student = MutableStateFlow(Student())
    val teacher = MutableStateFlow(Teacher())
    val course = MutableStateFlow(Course())
    val lesson = MutableStateFlow(Lesson())
    val group = MutableStateFlow(Group())

    val topics = MutableStateFlow(listOf<Topic>())
    val students = MutableStateFlow(listOf<Student>())
    val teachers = MutableStateFlow(listOf<Teacher>())
    val courses = MutableStateFlow(listOf<Course>())
    val lessons = MutableStateFlow(listOf<Lesson>())
    val groups = MutableStateFlow(listOf<Group>())

    private val aLock = Mutex(false)
    fun alter(action: AppState.() -> Unit) {
        runBlocking {
            aLock.withLock {
                this@AppState.action()
            }
        }
    }
}