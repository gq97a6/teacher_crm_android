package org.labcluster.crm.android.mock

import org.labcluster.crm.android.app.App
import org.labcluster.crm.android.app.AppState
import org.labcluster.crm.shared.SharedMock

object Mock : SharedMock() {

    val state = AppState().apply {
        group.lessons.value = lessons
        groupList.groups.value = groups
        topic.setTopic(topics.random())
        group.group.value = groups.random()

        groupList.nextLessons.value = groups.map { it.uuid }.associateWith {
            lessons.random()
        }

        lesson.setLesson(
            lessons.random().apply {
                epochStart = (System.currentTimeMillis() / 1000) - 1000
                duration = 1030
            }
        )
    }

    class MockApp : App() {
        override fun boot() {}
    }
}