package org.labcluster.crm

import org.labcluster.crm.app.AppState
import org.labcluster.crm.shared.Mock

object Mock {
    val state = AppState().apply {
        group.lessons.value = Mock.lessons
        groupList.groups.value = Mock.groups
        topic.setTopic(Mock.topics.random())
        group.group.value = Mock.groups.random()
        lesson.setLesson(
            Mock.lessons.random().copy(
                epochStart = (System.currentTimeMillis() / 1000) - 1000,
                duration = 1030
            )
        )
    }

    val lorem =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi metus dolor, lacinia quis lacinia id, sagittis eget augue. Nunc eget orci in orci sollicitudin congue. Phasellus ante justo, consectetur nec suscipit a, pretium at tortor. Aenean sollicitudin, turpis eget venenatis porttitor, augue arcu mattis metus, quis cursus massa quam sed dui. Etiam eleifend sollicitudin massa, rutrum pellentesque massa sodales lacinia. Etiam placerat eros vel metus sagittis scelerisque. Vestibulum id ante non justo ultricies bibendum sed sit amet sem. Fusce luctus in mi ac consectetur. Pellentesque quis posuere mi, in faucibus ligula. Praesent id vulputate metus. Phasellus ex ligula, dictum eget dignissim eu, mattis non lorem. Quisque nibh quam, finibus ut nunc et, mattis pharetra risus. Curabitur volutpat laoreet sapien eget pellentesque. Vestibulum elementum maximus auctor. Quisque congue gravida purus, id bibendum enim. Maecenas consequat pulvinar quam eu efficitur. Proin et posuere enim. Nam tempor purus sit amet semper elementum. Nunc rutrum congue varius. In hac habitasse platea dictumst. Etiam a egestas lectus, et condimentum nunc. Mauris rutrum vestibulum scelerisque. Phasellus gravida, quam non pellentesque dignissim, risus tellus tempor libero, eget commodo enim nisi quis erat. Ut quis augue et diam rhoncus placerat et ac nulla. Praesent vitae sem eget massa."
}