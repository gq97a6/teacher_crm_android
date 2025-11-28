package org.labcluster.crm.android.screen.setting

import androidx.lifecycle.ViewModel
import org.labcluster.crm.android.LoginViewKey
import org.labcluster.crm.android.Open
import org.labcluster.crm.android.app.App
import org.labcluster.crm.android.app.AppApi
import org.labcluster.crm.android.app.AppState
import org.labcluster.crm.android.screen.calendar.CalendarViewModelState
import org.labcluster.crm.android.screen.group.GroupViewModelState
import org.labcluster.crm.android.screen.grouplist.GroupListViewModelState
import org.labcluster.crm.android.screen.lesson.LessonViewModelState
import org.labcluster.crm.android.screen.login.LoginViewModelState
import org.labcluster.crm.android.screen.topic.TopicViewModelState
import org.labcluster.crm.android.storage.Storage.dumpToFile

@Open
class SettingViewModel(val state: AppState = App.state) : ViewModel() {

    fun onSavedClicked() {
    }

    fun onChangePasswordClicked() {
    }

    fun onLogout() {
        state.alter {
            backstack.value.clear()
            backstack.value.add(LoginViewKey())
            isNavigationEnabled.value = false
            calendar = CalendarViewModelState()
            group = GroupViewModelState()
            groupList = GroupListViewModelState()
            lesson = LessonViewModelState()
            topic = TopicViewModelState()
            login = LoginViewModelState()
            apiState = AppApi.State()

            state.dumpPath?.ifBlank { null }?.let {
                state.dumpToFile(it)
            }
        }
    }
}