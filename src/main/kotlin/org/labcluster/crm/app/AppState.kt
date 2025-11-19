package org.labcluster.crm.app

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.labcluster.crm.GroupViewKey
import org.labcluster.crm.chronos.Chronos
import org.labcluster.crm.shared.model.Course
import org.labcluster.crm.shared.model.Group
import org.labcluster.crm.shared.model.Lesson
import org.labcluster.crm.shared.model.Student
import org.labcluster.crm.shared.model.Teacher
import org.labcluster.crm.shared.model.Topic

open class AppState {
    open val chronos = Chronos()
    open val backstack = MutableStateFlow(NavBackStack<NavKey>(GroupViewKey()))

    open val topic = MutableStateFlow(Topic())
    open val student = MutableStateFlow(Student())
    open val teacher = MutableStateFlow(Teacher())
    open val course = MutableStateFlow(Course())
    open val lesson = MutableStateFlow(Lesson())
    open val group = MutableStateFlow(Group())

    open val topics = MutableStateFlow(listOf<Topic>())
    open val students = MutableStateFlow(listOf<Student>())
    open val teachers = MutableStateFlow(listOf<Teacher>())
    open val courses = MutableStateFlow(listOf<Course>())
    open val lessons = MutableStateFlow(listOf<Lesson>())
    open val groups = MutableStateFlow(listOf<Group>())

    private val aLock = Mutex(false)
    fun alter(action: suspend AppState.() -> Unit) {
        runBlocking {
            aLock.withLock {
                this@AppState.action()
            }
        }
    }
}