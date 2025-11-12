package org.labcluster.crm.app

import android.app.Application
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.labcluster.crm.shared.Database
import org.labcluster.crm.shared.Mock
import org.labcluster.crm.shared.repository.CourseRepository.save
import org.labcluster.crm.shared.repository.LessonRepository.save
import org.labcluster.crm.shared.repository.StudentRepository.save
import org.labcluster.crm.shared.repository.TeacherRepository.save
import org.labcluster.crm.shared.repository.TopicRepository.save


class App : Application() {
    companion object {
        var app = App()
        var state = AppState()
        lateinit var db: Database
    }

    override fun onCreate() {
        super.onCreate()
        app = this

        val driver = AndroidSqliteDriver(
            schema = Database.Schema,
            context = baseContext,
            name = "debug.db"
        )

        db = Database(driver)

        CoroutineScope(Dispatchers.IO).launch {
            Mock.courses.save(db)
            Mock.students.save(db)
            Mock.lessons.save(db)
            Mock.teachers.save(db)
            Mock.topics.save(db)
        }
    }
}