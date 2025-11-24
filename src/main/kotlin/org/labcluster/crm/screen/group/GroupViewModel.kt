@file:UseContextualSerialization(MutableStateFlow::class)

package org.labcluster.crm.screen.group

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseContextualSerialization
import org.labcluster.crm.LessonViewKey
import org.labcluster.crm.Open
import org.labcluster.crm.app.App
import org.labcluster.crm.app.AppState
import org.labcluster.crm.shared.model.Group
import org.labcluster.crm.shared.model.Lesson

@Open
class GroupViewModel(val state: AppState = App.state) : ViewModel() {

    @Open
    @Serializable
    class State() {
        val isGroupSet = MutableStateFlow(false)
        val group = MutableStateFlow(Group())
        val lessons = MutableStateFlow(listOf<Lesson>())
    }

    fun lessonOnClick(lessonClicked: Lesson) {
        state.alter {
            lesson.lesson.value = lessonClicked
            backstack.value += LessonViewKey()
        }
    }

    fun onBackClicked() {
        state.alter {
            backstack.value.dropLast(1)
        }
    }
}