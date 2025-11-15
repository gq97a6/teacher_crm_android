package org.labcluster.crm.app

import android.app.Application
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.labcluster.crm.objects.Mock
import org.labcluster.crm.shared.Database
import org.labcluster.crm.shared.repository.CourseRepository
import org.labcluster.crm.shared.repository.GroupRepository
import org.labcluster.crm.shared.repository.LessonRepository
import org.labcluster.crm.shared.repository.StudentRepository
import org.labcluster.crm.shared.repository.TeacherRepository
import org.labcluster.crm.shared.repository.TopicRepository
import org.labcluster.crm.shared.Mock as SharedMock


class App : Application() {
    internal companion object {
        var app = App()
        var state = AppState()

        lateinit var db: Database
        var courseRep = CourseRepository()
        var groupRep = GroupRepository()
        var studentRep = StudentRepository()
        var lessonRep = LessonRepository()
        var teacherRep = TeacherRepository()
        var topicRep = TopicRepository()
    }

    override fun onCreate() {
        super.onCreate()
        app = this

        val driver = AndroidSqliteDriver(
            schema = Database.Schema,
            context = app.baseContext,
            name = "debug.db"
        )

        //Initialize database
        db = Database(driver)

        //Initialize repositories
        courseRep = CourseRepository(db)
        groupRep = GroupRepository(db)
        studentRep = StudentRepository(db)
        lessonRep = LessonRepository(db)
        teacherRep = TeacherRepository(db)
        topicRep = TopicRepository(db)

        //Insert mockups
        state = Mock.state
        CoroutineScope(Dispatchers.IO).launch {
            courseRep.insert(SharedMock.courses)
            studentRep.insert(SharedMock.students)
            lessonRep.insert(SharedMock.lessons)
            teacherRep.insert(SharedMock.teachers)
            topicRep.insert(SharedMock.topics)
        }
    }
}