package org.labcluster.crm.objects

import org.labcluster.crm.app.AppState
import org.labcluster.crm.shared.Mock
import org.labcluster.crm.shared.model.Student

object Mock {
    val spanishStudent = Student(
        "Juan Antonio José",
        "García Fernández López Martínez Sánchez"
    )

    val state = AppState().apply {
        topics.value = Mock.topics
        students.value = Mock.students
        teachers.value = Mock.teachers
        courses.value = Mock.courses
        lessons.value = Mock.lessons
        groups.value = Mock.groups
        topic.value = Mock.topics.random()
        student.value = Mock.students.random()
        teacher.value = Mock.teachers.random()
        course.value = Mock.courses.random()
        group.value = Mock.groups.random()
        lesson.value = Mock.lessons.random()
    }
}