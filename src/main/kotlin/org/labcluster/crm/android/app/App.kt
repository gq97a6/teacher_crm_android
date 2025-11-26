package org.labcluster.crm.android.app

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.labcluster.crm.android.CalendarViewKey
import org.labcluster.crm.android.LoginViewKey
import org.labcluster.crm.android.Storage.getFromFile


class App : Application() {
    internal companion object {
        lateinit var app: App
        lateinit var state: AppState
        lateinit var api: AppApi
    }

    override fun onCreate() {
        super.onCreate()
        app = this

        //Configure path to state dump
        val dumpPath = "${app.filesDir.canonicalPath}/stateDump"

        //Recover state dump or create new one
        state = AppState()
        state = getFromFile(dumpPath) ?: AppState()
        state.dumpPath = dumpPath
        api = AppApi(state, "https://crm.labcluster.org/api")

        CoroutineScope(Dispatchers.IO).launch {
            val timerJob = launch { delay(1000) }
            onColdStart()
            timerJob.join()

            //Check if already authorized
            if (state.login.isAuthorized.value) {
                state.backstack.value.clear()
                state.backstack.value.add(CalendarViewKey())
                state.isNavigationEnabled.value = true
            } else {
                state.backstack.value.clear()
                state.backstack.value.add(LoginViewKey())
            }
        }
    }

    suspend fun onColdStart() {
        //Fetch update
        if (!state.login.isAuthorized.value) {
            state.calendar.fetch()
            state.groupList.fetch()
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