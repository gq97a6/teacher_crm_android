package org.labcluster.crm.android.app

import org.labcluster.crm.android.CalendarViewKey
import org.labcluster.crm.android.LoginViewKey
import org.labcluster.crm.android.SplashViewKey
import org.labcluster.crm.android.app.App.Companion.api
import org.labcluster.crm.android.app.App.Companion.app
import org.labcluster.crm.android.app.App.Companion.state
import org.labcluster.crm.android.storage.Storage.getFromFile

object AppStartup {

    //Run on app boot
    suspend fun boot() {
        setupState()
        start()
    }

    //Run after login
    suspend fun start() {
        state.lockState()
        displaySplash()
        setupApi()
        updateState()
        navigate()
        state.unlockState()
    }

    private fun setupState() {
        //Get path to state dump
        val dumpPath = "${app.filesDir.canonicalPath}/stateDump"

        //Recover state dump or create new one
        state = getFromFile(dumpPath) ?: AppState()
        state.dumpPath = dumpPath
    }

    private fun displaySplash() {
        state.backstack.value.clear()
        state.backstack.value.add(SplashViewKey())
    }

    private fun setupApi() {
        api = AppApi("https://crm.labcluster.org/api")
    }

    private suspend fun updateState() {
        if (state.login.authToken.value.isEmpty()) return

        state.calendar.fetch()
        state.groupList.fetch()

        //Refetch lesson
        api.getLesson(state.lesson.lesson.value.uuid)?.let {
            state.lesson.setLesson(it)
        }

        //Refetch topic
        api.getTopic(state.topic.topic.value.uuid)?.let {
            state.topic.setTopic(it)
        }

        //Refetch group
        api.getGroup(state.group.group.value.uuid)?.let {
            state.group.setGroup(it)
        }
    }

    private fun navigate() {
        if (state.login.authToken.value.isEmpty()) {
            state.isNavigationEnabled.value = false
            state.backstack.value.clear()
            state.backstack.value.add(LoginViewKey())
        } else {
            state.backstack.value.clear()
            state.backstack.value.add(CalendarViewKey())
            state.isNavigationEnabled.value = true
        }
    }
}