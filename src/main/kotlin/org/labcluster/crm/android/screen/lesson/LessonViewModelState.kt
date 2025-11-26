@file:UseContextualSerialization(MutableStateFlow::class)

package org.labcluster.crm.android.screen.lesson

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseContextualSerialization
import org.labcluster.crm.android.Open
import org.labcluster.crm.shared.model.Lesson

@Open
@Serializable
class LessonViewModelState() {
    val isLessonSet = MutableStateFlow(false)
    val lesson = MutableStateFlow(Lesson())

    fun setLesson(lesson: Lesson) {
        this.lesson.value = lesson
        isLessonSet.value = true
    }
}