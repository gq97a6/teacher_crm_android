package org.labcluster.crm.android.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.labcluster.crm.android.app.App
import org.labcluster.crm.android.app.AppApi
import org.labcluster.crm.android.app.AppStartup
import org.labcluster.crm.android.app.AppState
import org.labcluster.crm.shared.Open

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
                login.login()
            }

            AppStartup.start()
            timerJob.join()
        }
    }

    fun onRegister() {

    }
}