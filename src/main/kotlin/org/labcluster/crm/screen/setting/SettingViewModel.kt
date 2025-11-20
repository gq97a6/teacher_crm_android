package org.labcluster.crm.screen.setting

import androidx.lifecycle.ViewModel
import org.labcluster.crm.Open
import org.labcluster.crm.app.App
import org.labcluster.crm.app.AppState

@Open
class SettingViewModel(val state: AppState = App.state) : ViewModel() {

    @Open
    class State()

    fun onSavedClicked() {

    }

    fun onChangePasswordClicked() {

    }
}