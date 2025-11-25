@file:UseContextualSerialization(MutableStateFlow::class)

package org.labcluster.crm.android.screen.setting

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.UseContextualSerialization
import org.labcluster.crm.android.Open
import org.labcluster.crm.android.app.App
import org.labcluster.crm.android.app.AppState

@Open
class SettingViewModel(val state: AppState = App.state) : ViewModel() {

    @Open
    class State()

    fun onSavedClicked() {

    }

    fun onChangePasswordClicked() {

    }
}