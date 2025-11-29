package org.labcluster.crm.android.screen.grouplist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.labcluster.crm.android.GroupViewKey
import org.labcluster.crm.android.app.App
import org.labcluster.crm.android.app.AppApi
import org.labcluster.crm.android.app.AppState
import org.labcluster.crm.shared.Open
import org.labcluster.crm.shared.model.Group

@Open
class GroupListViewModel(
    val state: AppState = App.state,
    val api: AppApi = App.api
) : ViewModel() {

    val isLoadingShown = MutableStateFlow(false)

    fun groupOnClick(clickedGroup: Group) {
        viewModelScope.launch {
            isLoadingShown.value = true

            state.alter(viewModelScope) { group.setGroup(clickedGroup) }.join()

            //Navigate to group view
            state.alter(viewModelScope) {
                state.backstack.value.add(GroupViewKey())
            }

            //Prevent list entry flash
            delay(50)

            isLoadingShown.value = false
        }
    }

    fun onRefreshClicked() {
        val alterJob = state.alter(viewModelScope) {
            state.groupList.fetch()
        }

        viewModelScope.launch {
            val timerJob = launch { delay(1000) }
            isLoadingShown.value = true
            alterJob.join()
            timerJob.join()
            isLoadingShown.value = false
        }
    }
}