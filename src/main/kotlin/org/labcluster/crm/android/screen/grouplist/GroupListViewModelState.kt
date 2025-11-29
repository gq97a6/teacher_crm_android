@file:UseContextualSerialization(MutableStateFlow::class)

package org.labcluster.crm.android.screen.grouplist

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseContextualSerialization
import org.labcluster.crm.android.app.App
import org.labcluster.crm.android.app.AppApi
import org.labcluster.crm.android.app.AppState
import org.labcluster.crm.shared.Open
import org.labcluster.crm.shared.model.Group
import org.labcluster.crm.shared.model.Lesson

@Open
@Serializable
class GroupListViewModelState() {

    val groups = MutableStateFlow(listOf<Group>())
    val nextLessons = MutableStateFlow(mapOf<String, Lesson?>())

    suspend fun fetch(state: AppState = App.state, api: AppApi = App.api) {
        api.getGroupsTaughtBy(state.login.teacher.value.uuid).let {
            groups.value = it
        }

        buildMap {
            groups.value.forEach { group ->
                val nextLesson = api.getGroupNextLesson(group.uuid)
                set(group.uuid, nextLesson)
            }
        }.let { nextLessons.value = it }
    }
}