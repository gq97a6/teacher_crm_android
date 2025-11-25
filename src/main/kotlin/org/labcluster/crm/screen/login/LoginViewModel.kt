@file:UseContextualSerialization(MutableStateFlow::class)

package org.labcluster.crm.screen.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseContextualSerialization
import org.labcluster.crm.CalendarViewKey
import org.labcluster.crm.Open
import org.labcluster.crm.app.App
import org.labcluster.crm.app.AppApi
import org.labcluster.crm.app.AppState
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