@file:UseContextualSerialization(MutableStateFlow::class)

package org.labcluster.crm.screen.grouplist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseContextualSerialization
import org.labcluster.crm.GroupViewKey
import org.labcluster.crm.Open
import org.labcluster.crm.app.App
import org.labcluster.crm.app.AppState
import org.labcluster.crm.shared.Mock
import org.labcluster.crm.shared.model.Group
import org.labcluster.crm.shared.model.Lesson

@Open
class GroupListViewModel(val state: AppState = App.state) : ViewModel() {

    @Open
    @Serializable
    class State() {
        val groups = MutableStateFlow(listOf<Group>())
    }

    val groupsWithNextLesson: StateFlow<Map<Group, Lesson>> = state.groupList.groups.map { groups ->
        groups.associateWith {
            Mock.lessons.shuffled().random()
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyMap()
    )

    fun groupOnClick(clickedGroup: Group) {
        state.alter {
            group.group.value = clickedGroup
            group.lessons.value = Mock.lessons.shuffled().take(10)
            backstack.value += GroupViewKey()
        }
    }
}