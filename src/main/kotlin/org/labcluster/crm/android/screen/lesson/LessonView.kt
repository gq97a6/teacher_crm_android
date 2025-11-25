package org.labcluster.crm.android.screen.lesson

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.datetime.toInstant
import org.labcluster.crm.android.composable.PreviewScaffold
import org.labcluster.crm.android.screen.lesson.compose.LessonAppBar
import org.labcluster.crm.android.screen.lesson.compose.LessonContent
import org.labcluster.crm.android.screen.lesson.compose.LessonToolbarConfirm
import org.labcluster.crm.android.screen.lesson.compose.LessonToolbarEdit
import org.labcluster.crm.android.screen.lesson.compose.LessonToolbarStart
import org.labcluster.crm.shared.epochEnd
import org.labcluster.crm.shared.model.Course
import org.labcluster.crm.shared.model.Teacher

@Preview
@Composable
private fun Preview() = PreviewScaffold(false) { LessonView() }

@Composable
fun BoxScope.LessonView(vm: LessonViewModel = viewModel()) {

    val lesson by vm.state.lesson.lesson.collectAsStateWithLifecycle()
    val timeZone by vm.state.chronos.timeZone.collectAsStateWithLifecycle()
    val clock by vm.clock.collectAsStateWithLifecycle()
    val attendance by vm.attendance.collectAsStateWithLifecycle()
    val isEditable by vm.isEditable.collectAsStateWithLifecycle()

    val isLive by remember {
        derivedStateOf {
            clock.toInstant(timeZone).epochSeconds in lesson.epochStart..lesson.epochEnd()
        }
    }

    Scaffold(
        modifier = Modifier,
        topBar = {
            LessonAppBar(
                lesson = lesson,
                timeZone = timeZone,
                isLive = isLive,
                onShowTopic = vm::onShowTopic,
                onShowCourse = vm::onShowCourse,
            )
        },
        contentWindowInsets = WindowInsets(left = 15.dp, right = 15.dp)
    ) { paddingValues ->
        LessonContent(
            paddingValues = paddingValues,
            teacher = lesson.teacher1 ?: Teacher(),
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