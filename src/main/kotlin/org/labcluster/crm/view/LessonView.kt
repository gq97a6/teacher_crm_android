package org.labcluster.crm.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.labcluster.crm.PreviewSample
import org.labcluster.crm.composable.lesson.LessonAppBar
import org.labcluster.crm.composable.lesson.LessonContent
import org.labcluster.crm.composable.lesson.LessonToolbarConfirm
import org.labcluster.crm.composable.lesson.LessonToolbarEdit
import org.labcluster.crm.composable.lesson.LessonToolbarStart
import org.labcluster.crm.shared.model.Course
import org.labcluster.crm.shared.model.Teacher
import org.labcluster.crm.viewmodel.LessonViewModel

@Preview
@Composable
private fun Preview() = PreviewSample { LessonView() }

@Composable
fun LessonView(vm: LessonViewModel = viewModel()) {

    val group by vm.state.group.collectAsStateWithLifecycle()
    val lesson by vm.state.lesson.collectAsStateWithLifecycle()
    val timeZone by vm.state.timeZone.collectAsStateWithLifecycle()

    val attendance by vm.attendance.collectAsStateWithLifecycle()
    val isEditable by vm.isEditable.collectAsStateWithLifecycle()

    Box(Modifier.fillMaxHeight()) {
        Column {
            LessonAppBar(
                lesson = lesson,
                timeZone = timeZone,
                onShowTopic = vm::onShowTopic,
                onShowCourse = vm::onShowCourse,
            )
            LessonContent(
                teacher = group.teacher ?: Teacher(),
                students = lesson.students,
                attendance = attendance,
                hasBegun = lesson.epochBegin != null,
                isEditable = isEditable,
                course = lesson.course ?: Course(),
                topic = lesson.topic?.name ?: "",
                onStudentCheckbox = vm::onStudentCheckbox
            )
        }

        //Has begin and is not being edited (10)
        AnimatedVisibility(
            visible = lesson.epochBegin != null && !isEditable,
            enter = slideInVertically() + fadeIn(initialAlpha = 0.3f),
            exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp)
        ) {
            LessonToolbarEdit(
                onEditClicked = vm::onEditClicked,
                onTopicClicked = vm::onShowTopic,
                onCourseClicked = vm::onShowCourse
            )
        }

        //Has not begin and not being edited (00)
        AnimatedVisibility(
            visible = lesson.epochBegin == null && !isEditable,
            enter = slideInVertically() + fadeIn(initialAlpha = 0.3f),
            exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp)
        ) {
            LessonToolbarStart(
                onStartClicked = vm::onStartClicked,
                onTopicClicked = vm::onShowTopic,
                onCourseClicked = vm::onShowCourse
            )
        }

        //Has not begin but is being edited (01)
        //Has begin and is being edited (11)
        AnimatedVisibility(
            visible = isEditable,
            enter = slideInVertically() + fadeIn(initialAlpha = 0.3f),
            exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp)
        ) {
            LessonToolbarConfirm(
                onConfirmClicked = vm::onConfirmClicked,
                onCancelClicked = vm::onCancelClicked
            )
        }
    }
}