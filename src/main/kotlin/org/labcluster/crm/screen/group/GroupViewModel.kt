package org.labcluster.crm.screen.group

import androidx.lifecycle.ViewModel
import org.labcluster.crm.LessonViewKey
import org.labcluster.crm.app.App
import org.labcluster.crm.app.AppState
import org.labcluster.crm.shared.model.Lesson

class GroupViewModel(val state: AppState = App.state) : ViewModel() {
    fun lessonOnClick(lessonClicked: Lesson) {
        state.alter {
            lesson.value = lessonClicked
            backstack.value += LessonViewKey()
        }
    }

    fun onBackClicked() {
        state.alter {
            backstack.value.removeLast()
        }
    }
}