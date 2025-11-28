@file:UseContextualSerialization(MutableStateFlow::class)

package org.labcluster.crm.android.screen.group

import android.content.ClipData
import androidx.compose.ui.platform.ClipEntry
import androidx.compose.ui.platform.Clipboard
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.UseContextualSerialization
import org.labcluster.crm.android.LessonViewKey
import org.labcluster.crm.android.Open
import org.labcluster.crm.android.app.App
import org.labcluster.crm.android.app.AppState
import org.labcluster.crm.shared.model.Lesson

@Open
class GroupViewModel(val state: AppState = App.state) : ViewModel() {

    fun lessonOnClick(lessonClicked: Lesson) {
        state.alter {
            lesson.setLesson(lessonClicked)
            backstack.value.add(LessonViewKey())
        }
    }

    fun onCopyUuid(clipboard: Clipboard) {
        viewModelScope.launch {
            val data = ClipData.newPlainText("uuid", state.group.group.value.uuid)
            val entry = ClipEntry(data)
            clipboard.setClipEntry(entry)
        }
    }
}