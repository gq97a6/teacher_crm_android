package org.labcluster.crm.android.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.labcluster.crm.android.CalendarViewKey
import org.labcluster.crm.android.Open
import org.labcluster.crm.android.app.App
import org.labcluster.crm.android.app.AppApi
import org.labcluster.crm.android.app.AppState
import org.labcluster.crm.shared.model.Teacher

@Open
class LoginViewModel(
    val state: AppState = App.state,
    val api: AppApi = App.api
) : ViewModel() {

    val isLoading = MutableStateFlow(false)

    fun onLogin() {
        viewModelScope.launch {
            val timerJob = launch { delay(1000) }
            isLoading.value = true

            //Login
            state.alter(viewModelScope) {
                login.teacher.update { Teacher() }
                login.isAuthorized.update { api.authorize() }
            }.join()

            //If authorized fetch updates and redirect
            if (state.login.isAuthorized.value) state.alter(viewModelScope) {
                state.calendar.fetch()
                state.groupList.fetch()
                backstack.value.clear()
                backstack.value.add(CalendarViewKey())
                isNavigationEnabled.value = true
            }.join()

            timerJob.join()

            //Prevent login button flash
            delay(50)

            isLoading.value = false
        }
    }

    fun onRegister() {

    }
}