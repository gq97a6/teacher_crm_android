@file:UseContextualSerialization(MutableStateFlow::class)

package org.labcluster.crm.screen.grouplist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseContextualSerialization
import org.labcluster.crm.GroupViewKey
import org.labcluster.crm.Open
import org.labcluster.crm.app.App
import org.labcluster.crm.app.AppState
import org.labcluster.crm.shared.model.Group
import org.labcluster.crm.shared.model.Lesson

@Open
class GroupListViewModel(val state: AppState = App.state) : ViewModel() {

    @Open
    @Serializable
    class State() {
        val groups = MutableStateFlow(listOf<Group>())
    }

    val isLoadingShown = MutableStateFlow(false)

    val groupsWithNextLesson: StateFlow<Map<Group, Lesson>> = state.groupList.groups.map { groups ->
        groups.associateWith {
            Lesson()
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyMap()
    )

    fun groupOnClick(clickedGroup: Group) {
        state.alter {
            group.group.value = clickedGroup
            group.lessons.value = listOf()
            backstack.value.add(GroupViewKey())
        }
    }

    fun onRefreshClicked() {
        viewModelScope.launch {
            isLoadingShown.value = true
            delay(2000)
            state.alter {
                groupList.groups.value = listOf()
            }
            isLoadingShown.value = false
        }
    }
}