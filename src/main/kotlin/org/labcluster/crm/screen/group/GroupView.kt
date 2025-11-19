package org.labcluster.crm.screen.group

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.labcluster.crm.composable.shared.PreviewScaffold
import org.labcluster.crm.screen.group.compose.GroupList
import org.labcluster.crm.screen.group.compose.GroupListAppBar
import org.labcluster.crm.screen.group.compose.GroupToolbar

@Preview
@Composable
private fun Preview() = PreviewScaffold(false) { GroupView() }

@Composable
fun BoxScope.GroupView(vm: GroupViewModel = viewModel()) {
    var isLesson by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier,
        topBar = {
            GroupListAppBar()
        },
        contentWindowInsets = WindowInsets(left = 15.dp, right = 10.dp)
    ) { paddingValues ->
        GroupList(
            paddingValues = paddingValues,
            //groupsWithLessons = TODO(),
            groupOnClick = {

            }
        )
    }

    GroupToolbar(
        lessonOnClick = { isLesson = true },
        studentsOnClick = { isLesson = false }
    )
}