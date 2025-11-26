@file:UseContextualSerialization(MutableStateFlow::class)

package org.labcluster.crm.android.screen.group

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseContextualSerialization
import org.labcluster.crm.android.Open
import org.labcluster.crm.android.app.App
import org.labcluster.crm.android.app.AppApi
import org.labcluster.crm.shared.model.Group
import org.labcluster.crm.shared.model.Lesson

@Open
@Serializable
class GroupViewModelState() {
    val group = MutableStateFlow(Group())
    val lessons = MutableStateFlow(listOf<Lesson>())

    suspend fun fetch(group: Group, api: AppApi = App.api) {
        this.group.value = group
        api.fetchGroupTimetable(group.uuid).let {
            lessons.value = it
        }
    }
}