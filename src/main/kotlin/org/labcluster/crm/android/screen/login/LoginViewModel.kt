@file:UseContextualSerialization(MutableStateFlow::class)

package org.labcluster.crm.android.screen.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseContextualSerialization
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

    @Open
    @Serializable
    class State() {
        val isAuthorized = MutableStateFlow(false)
        val teacher = MutableStateFlow(Teacher())
    }

    fun onLogin() {
        state.alter {
            login.teacher.update { Teacher() }
            login.isAuthorized.update { api.authorize() }
        }

        if (state.login.isAuthorized.value) state.alter {
            backstack.value.clear()
            backstack.value.add(CalendarViewKey())
            isNavigationEnabled.value = true
        }
    }

    fun onRegister() {

    }
}