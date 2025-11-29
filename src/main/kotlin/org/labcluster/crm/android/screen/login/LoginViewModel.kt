package org.labcluster.crm.android.screen.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
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

    fun onLogin(uuid: String) {
        GlobalScope.launch {
            val timerJob = launch { delay(1000) }
            isLoading.value = true

            var loginResult = false
            state.alter(GlobalScope) {
                loginResult = login.login(uuid)
            }.join()

            if (loginResult) AppStartup.start()
            timerJob.join()
            isLoading.value = false
        }
    }
}