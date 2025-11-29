package org.labcluster.crm.android.screen.grouplist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.labcluster.crm.android.GroupViewKey
import org.labcluster.crm.android.app.App
import org.labcluster.crm.android.app.AppApi
import org.labcluster.crm.android.app.AppState
import org.labcluster.crm.shared.Open
import org.labcluster.crm.shared.model.Group
import org.labcluster.crm.shared.model.Lesson

@Open
class GroupListViewModel(
    val state: AppState = App.state,
    val api: AppApi = App.api
) : ViewModel() {

    val isLoadingShown = MutableStateFlow(false)

    val groupLesson: StateFlow<List<Pair<Group, Lesson>>> = combine(
        state.groupList.groups,
        state.groupList.nextLessons
    ) { groups, nextLessons ->
        val map = groups.mapNotNull { group ->
            nextLessons[group.uuid]?.let { group to it }
        }

        map.sortedWith(
            comparator = compareBy({ it.first.dayIndex }, { it.first.timeEpoch })
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = listOf()
    )

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