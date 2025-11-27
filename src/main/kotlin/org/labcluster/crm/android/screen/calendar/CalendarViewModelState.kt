@file:UseContextualSerialization(MutableStateFlow::class)

package org.labcluster.crm.android.screen.calendar

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseContextualSerialization
import org.labcluster.crm.android.Open
import org.labcluster.crm.android.app.App
import org.labcluster.crm.android.app.AppApi
import org.labcluster.crm.android.app.AppState
import org.labcluster.crm.shared.model.Lesson


@Open
@Serializable
class CalendarViewModelState() {
    val lessons = MutableStateFlow(listOf<Lesson>())

    suspend fun fetch(state: AppState = App.state, api: AppApi = App.api) {
        api.getTeacherTimetable(state.login.teacher.value.uuid).let {
            lessons.value = it
        }
    }
}