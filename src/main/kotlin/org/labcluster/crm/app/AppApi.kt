package org.labcluster.crm.app

import kotlinx.serialization.Serializable
import org.labcluster.crm.Open
import org.labcluster.crm.shared.Mock
import org.labcluster.crm.shared.model.Group
import org.labcluster.crm.shared.model.Lesson

@Open
class AppApi(val state: AppState) {

    @Open
    @Serializable
    class State()

    fun authorize(): Boolean {
        return true
    }

    fun fetchTeacherTimetable(teacherUuid: String): List<Lesson> = Mock.lessons
    fun fetchGroupTimetable(groupUuid: String): List<Lesson> = Mock.lessons
    fun fetchGroupsTaughtBy(teacherUuid: String): List<Group> = Mock.groups
    fun fetchGroupNextLesson(groupUuid: String): Lesson? = Mock.lessons.random()

    fun fetchGroupNextLessonMap(): Map<Group, Lesson> {
        if (!state.login.isAuthorized.value) return mapOf()

        //Fetch group list and pair it with its nextLesson
        return fetchGroupsTaughtBy(
            teacherUuid = state.login.teacher.value.uuid
        ).associateWith { group ->
            (fetchGroupNextLesson(group.uuid) ?: Lesson())
        }
    }
}