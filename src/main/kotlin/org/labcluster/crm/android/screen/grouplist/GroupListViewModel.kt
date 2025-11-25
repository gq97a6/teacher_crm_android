@file:UseContextualSerialization(MutableStateFlow::class)

package org.labcluster.crm.android.screen.grouplist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseContextualSerialization
import org.labcluster.crm.android.GroupViewKey
import org.labcluster.crm.android.Open
import org.labcluster.crm.android.app.App
import org.labcluster.crm.android.app.AppApi
import org.labcluster.crm.android.app.AppState
import org.labcluster.crm.shared.model.Group
import org.labcluster.crm.shared.model.Lesson

@Open
class GroupListViewModel(
    val state: AppState = App.state,
    val api: AppApi = App.api
) : ViewModel() {

    @Open
    @Serializable
    class State() {
        val groups = MutableStateFlow(listOf<Group>())
        val lessons = MutableStateFlow(mapOf<String, Lesson?>())
    }

    val isLoadingShown = MutableStateFlow(false)

    fun groupOnClick(clickedGroup: Group) {
        viewModelScope.launch {
            isLoadingShown.value = true

            state.alter {
                group.group.value = clickedGroup
                group.lessons.value = api.fetchGroupTimetable(clickedGroup.uuid)
                backstack.value.add(GroupViewKey())
            }

            delay(100)
            isLoadingShown.value = false
        }
    }

    fun onRefreshClicked() {
        viewModelScope.launch {
            isLoadingShown.value = true

            state.alter {
                val currentTeacherUuid = login.teacher.value.uuid
                val fetchedGroups = api.fetchGroupsTaughtBy(currentTeacherUuid)
                groupList.groups.value = fetchedGroups

                val newLessonMap = mutableMapOf<String, Lesson?>()
                fetchedGroups.forEach { group ->
                    val nextLesson = api.fetchGroupNextLesson(group.uuid)
                    newLessonMap[group.uuid] = nextLesson
                }
                groupList.lessons.value = newLessonMap
            }

            delay(1000)
            isLoadingShown.value = false
        }
    }
}