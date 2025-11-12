package org.labcluster.crm.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.labcluster.crm.PreviewSample
import org.labcluster.crm.composable.lesson.LessonAppBar
import org.labcluster.crm.composable.lesson.LessonContent
import org.labcluster.crm.composable.lesson.LessonToolbar
import org.labcluster.crm.viewmodel.LessonViewModel

@Preview
@Composable
private fun Preview() = PreviewSample { LessonView() }

@Composable
fun LessonView(vm: LessonViewModel = viewModel()) {

    val students by vm.students.collectAsStateWithLifecycle()
    val hasBegun by vm.hasBegun.collectAsStateWithLifecycle()
    val course by vm.course.collectAsStateWithLifecycle()
    val topics by vm.topics.collectAsStateWithLifecycle()
    val topic by vm.topic.collectAsStateWithLifecycle()
    val isMenuExpanded by vm.isMenuExpanded.collectAsStateWithLifecycle()

    Box(Modifier.fillMaxHeight()) {
        Column {
            LessonAppBar(
                isMenuExpanded = isMenuExpanded,
                onDropdownMenuDismissed = vm::onDropdownMenuDismissed,
                onMenuClicked = vm::onMenuClicked,
                onEditLesson = vm::onEditLesson,
                onShowTopic = vm::onShowTopic,
                onShowCourse = vm::onShowCourse,
            )
            LessonContent(
                students = students,
                hasBegun = hasBegun,
                course = course,
                topic = topic,
                topics = topics,
                onSetTopic = vm::onSetTopic
            )
        }
        LessonToolbar(hasBegun)
    }
}