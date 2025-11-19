package org.labcluster.crm.screen.grouplist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.labcluster.crm.GroupViewKey
import org.labcluster.crm.app.App
import org.labcluster.crm.app.AppState
import org.labcluster.crm.shared.Mock
import org.labcluster.crm.shared.model.Group
import org.labcluster.crm.shared.model.Lesson

class GroupListViewModel(val state: AppState = App.state) : ViewModel() {
    val groupsWithNextLesson: StateFlow<Map<Group, Lesson>> = state.groups.map { groups ->
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
            group.value = clickedGroup
            lessons.value = Mock.lessons.shuffled().take(10)
            backstack.value += GroupViewKey()
        }
    }
}