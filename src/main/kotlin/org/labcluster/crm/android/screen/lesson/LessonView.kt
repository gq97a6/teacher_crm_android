package org.labcluster.crm.android.screen.lesson

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.datetime.toInstant
import org.labcluster.crm.android.composable.Loading
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
private fun Preview() = PreviewScaffold(false, doMock = true) { LessonView() }

@Composable
fun BoxScope.LessonView(vm: LessonViewModel = viewModel()) {

    val lesson by vm.displayedLesson.collectAsStateWithLifecycle()
    val timeZone by vm.state.chronos.timeZone.collectAsStateWithLifecycle()
    val clock by vm.clock.collectAsStateWithLifecycle()
    val attendance by vm.attendance.collectAsStateWithLifecycle()
    val isEditable by vm.isEditable.collectAsStateWithLifecycle()
    val isLoading by vm.isLoading.collectAsStateWithLifecycle()

    val isLive by remember {
        derivedStateOf {
            clock.toInstant(timeZone).epochSeconds in lesson.epochStart..lesson.epochEnd()
        }
    }

    Scaffold(
        modifier = Modifier.blur(if (isLoading) 5.dp else 0.dp),
        topBar = {
            LessonAppBar(
                lesson = lesson,
                timeZone = timeZone,
                isLive = isLive,
                onShowTopic = vm::onShowTopic,
                onCopyUuid = vm::onCopyUuid,
            )
        },
        contentWindowInsets = WindowInsets(left = 15.dp, right = 15.dp)
    ) { paddingValues ->
        Column(Modifier.padding(paddingValues)) {
            LessonContent(
                students = lesson.students,
                attendance = attendance,
                hasBegun = lesson.epochBegin != null,
                isEditable = isEditable,
                onStudentCheckbox = vm::onStudentCheckbox,
                teacher = lesson.teacher1 ?: Teacher("Brak nauczyciela lekcji"),
                course = lesson.course ?: Course("Brak kursu lekcji"),
                topic = lesson.topic?.name ?: "Brak tematu lekcji"
            )
        }
    }

    //Has begin and is not being edited (10)
    AnimatedVisibility(
        visible = lesson.epochBegin != null && !isEditable && !isLoading,
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
        visible = lesson.epochBegin == null && !isEditable && !isLoading,
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
        visible = isEditable && !isLoading,
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

    if (isLoading) Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black.copy(0.2f))
    ) {
        Loading()
    }
}