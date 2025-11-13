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
import org.labcluster.crm.objects.Mock.spanishStudent
import org.labcluster.crm.shared.model.Course
import org.labcluster.crm.viewmodel.LessonViewModel

@Preview
@Composable
private fun Preview() = PreviewSample { LessonView() }

@Composable
fun LessonView(vm: LessonViewModel = viewModel()) {

    val teacher by vm.state.teacher.collectAsStateWithLifecycle()
    val lesson by vm.state.lesson.collectAsStateWithLifecycle()
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
                teacher = teacher,
                students = lesson.attendees,
                hasBegun = lesson.epochBegin != null,
                course = lesson.course ?: Course(),
                topic = lesson.topic?.name ?: "",
                topics = lesson.course?.topics ?: listOf(),
                onSetTopic = vm::onSetTopic
            )
        }
        LessonToolbar(lesson.epochBegin != null)
    }
}