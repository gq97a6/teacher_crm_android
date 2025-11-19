package org.labcluster.crm.screen.group

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.labcluster.crm.composable.PreviewScaffold
import org.labcluster.crm.screen.group.compose.GroupAppBar
import org.labcluster.crm.screen.group.compose.GroupLessonList
import org.labcluster.crm.screen.group.compose.GroupStudentList
import org.labcluster.crm.screen.group.compose.GroupTab
import org.labcluster.crm.shared.model.Lesson

@Preview
@Composable
private fun Preview() = PreviewScaffold(false) { GroupView() }

@Composable
fun GroupView(vm: GroupViewModel = viewModel()) {
    val group by vm.state.group.collectAsState()
    val lessons by vm.state.lessons.collectAsState()
    val timeZone by vm.state.chronos.timeZone.collectAsState()

    var selectedTabIndex by remember { mutableStateOf(0) }

    Scaffold(
        modifier = Modifier,
        topBar = {
            GroupAppBar(
                group = group,
                nextLesson = lessons.firstOrNull { it.epochBegin == null } ?: Lesson(),
                timeZone = timeZone,
                onBackClick = vm::onBackClicked
            )
        },
        contentWindowInsets = WindowInsets(left = 15.dp, right = 15.dp)
    ) { paddingValues ->
        Column {
            Spacer(Modifier.height(paddingValues.calculateTopPadding()))

            GroupTab(
                selectedTabIndex = selectedTabIndex,
                onLessonClicked = { selectedTabIndex = 0 },
                onStudentsClicked = { selectedTabIndex = 1 }
            )

            if (selectedTabIndex == 0) GroupLessonList(
                paddingValues,
                lessons = lessons,
                timeZone = timeZone,
                lessonOnClick = vm::lessonOnClick
            ) else GroupStudentList(
                paddingValues,
                students = group.students
            )
        }
    }
}