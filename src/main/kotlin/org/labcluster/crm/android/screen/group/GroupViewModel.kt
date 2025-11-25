@file:UseContextualSerialization(MutableStateFlow::class)

package org.labcluster.crm.android.screen.group

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseContextualSerialization
import org.labcluster.crm.android.LessonViewKey
import org.labcluster.crm.android.Open
import org.labcluster.crm.android.app.App
import org.labcluster.crm.android.app.AppState
import org.labcluster.crm.shared.model.Group
import org.labcluster.crm.shared.model.Lesson

@Open
class GroupViewModel(val state: AppState = App.state) : ViewModel() {

    @Open
    @Serializable
    class State() {
        val group = MutableStateFlow(Group())
        val lessons = MutableStateFlow(listOf<Lesson>())
    }

    fun lessonOnClick(lessonClicked: Lesson) {
        state.alter {
            lesson.setLesson(lessonClicked)
            backstack.value.add(LessonViewKey())
        }
    }

    fun onBackClicked() {
        state.alter {
            backstack.value.dropLast(1)
        }
    }
}