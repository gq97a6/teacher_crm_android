package org.labcluster.crm.app

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.labcluster.crm.CalendarViewKey
import org.labcluster.crm.LoginViewKey
import org.labcluster.crm.Storage.getFromFile
import org.labcluster.crm.shared.model.Lesson


class App : Application() {
    internal companion object {
        var app = App()
        var state = AppState()
        var api = AppApi(state)
    }

    override fun onCreate() {
        super.onCreate()
        app = this

        //Configure path to state dump
        val dumpPath = "${app.filesDir.canonicalPath}/stateDump"

        //Recover state dump or create new one
        state = getFromFile(dumpPath) ?: AppState()
        state.dumpPath = dumpPath
        api = AppApi(state)

        CoroutineScope(Dispatchers.IO).launch {
            val minDuration = launch { delay(1000) }
            onColdStart()
            minDuration.join()

            val isAuthorized = state.login.isAuthorized.value
            if (isAuthorized) {
                state.backstack.value.clear()
                state.isNavigationEnabled.value = true
                state.backstack.value.add(CalendarViewKey())
            } else {
                state.backstack.value.clear()
                state.backstack.value.add(LoginViewKey())
            }
        }
    }

    fun onColdStart() {
        //Fetch update
        if (state.login.isAuthorized.value) {
            val currentTeacherUuid = state.login.teacher.value.uuid
            val fetchedGroups = api.fetchGroupsTaughtBy(currentTeacherUuid)
            state.groupList.groups.value = fetchedGroups

            val newLessonMap = mutableMapOf<String, Lesson?>()
            fetchedGroups.forEach { group ->
                val nextLesson = api.fetchGroupNextLesson(group.uuid)
                newLessonMap[group.uuid] = nextLesson
            }
            state.groupList.lessons.value = newLessonMap
        }
    }
}

/*
Calendar
- check incoming lessons

Group
- check lessons for a group
- check list of students in that group
- go to a lesson of a group

GroupList
- check list of group
- go to a group

Lesson
- check attendance
- start lesson
- edit attendance
- check list of students
- go to topic
- check lesson info

Login
- auth via keycloak

 */