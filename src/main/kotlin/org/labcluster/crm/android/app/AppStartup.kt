package org.labcluster.crm.android.app

import org.labcluster.crm.android.CalendarViewKey
import org.labcluster.crm.android.LoginViewKey
import org.labcluster.crm.android.SplashViewKey
import org.labcluster.crm.android.app.App.Companion.api
import org.labcluster.crm.android.app.App.Companion.app
import org.labcluster.crm.android.app.App.Companion.state
import org.labcluster.crm.android.storage.Storage.getFromFile

object AppStartup {

    suspend fun boot() {
        setupState()
        displaySplash()
        setupApi()
        authorize()
        updateState()
        navigate()
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
        api = AppApi(state, "https://crm.labcluster.org/api")
    }

    private suspend fun authorize() {
        state.login.isAuthorized.value = api.getAuthorize()
    }

    private suspend fun updateState() {
        state.calendar.fetch()
        state.groupList.fetch()
    }

    private fun navigate() {
        state.isNavigationEnabled.value = true
        state.backstack.value.clear()
        state.backstack.value.add(CalendarViewKey())
        state.backstack.value.add(LoginViewKey())
    }
}