package org.labcluster.crm.screen.login

import androidx.lifecycle.ViewModel
import org.labcluster.crm.Open
import org.labcluster.crm.app.App
import org.labcluster.crm.app.AppState

@Open
class LoginViewModel(val state: AppState = App.state) : ViewModel() {

    @Open
    class State()

    fun onLogin() {

    }

    fun onRegister() {

    }
}