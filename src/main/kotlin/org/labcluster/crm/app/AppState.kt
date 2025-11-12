package org.labcluster.crm.app

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.labcluster.crm.shared.model.Course
import org.labcluster.crm.shared.model.Lesson
import org.labcluster.crm.shared.model.Student
import org.labcluster.crm.shared.model.Teacher
import org.labcluster.crm.shared.model.Topic

class AppState {

    val topics = MutableStateFlow(listOf<Topic>())
    val students = MutableStateFlow(listOf<Student>())
    val teachers = MutableStateFlow(listOf<Teacher>())
    val courses = MutableStateFlow(listOf<Course>())
    val lessons = MutableStateFlow(listOf<Lesson>())

    private val aLock = Mutex(false)
    suspend fun alter(action: () -> Unit) {
        aLock.withLock(action = action)
    }
}