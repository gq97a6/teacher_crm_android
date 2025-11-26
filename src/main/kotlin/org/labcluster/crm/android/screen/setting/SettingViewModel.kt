package org.labcluster.crm.android.screen.setting

import androidx.lifecycle.ViewModel
import org.labcluster.crm.android.Open
import org.labcluster.crm.android.app.App
import org.labcluster.crm.android.app.AppState

@Open
class SettingViewModel(val state: AppState = App.state) : ViewModel() {

    fun onSavedClicked() {
    }

    fun onChangePasswordClicked() {
    }
}