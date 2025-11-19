package org.labcluster.crm.screen.grouplist

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.labcluster.crm.composable.PreviewScaffold
import org.labcluster.crm.screen.grouplist.compose.GroupListAppBar
import org.labcluster.crm.screen.grouplist.compose.GroupListEntry

@Preview
@Composable
private fun Preview() = PreviewScaffold(false) { GroupListView() }

@Composable
fun GroupListView(vm: GroupListViewModel = viewModel()) {
    val timeZone by vm.state.chronos.timeZone.collectAsState()
    val groupsWithNextLesson by vm.groupsWithNextLesson.collectAsState()

    Scaffold(
        modifier = Modifier,
        topBar = { GroupListAppBar() },
        contentWindowInsets = WindowInsets(left = 15.dp, right = 15.dp)
    ) { paddingValues ->
        val topPadding = remember { paddingValues.calculateTopPadding() }
        val horizontal = remember { paddingValues.calculateStartPadding(LayoutDirection.Ltr) }

        LazyColumn(Modifier.padding(top = topPadding)) {
            groupsWithNextLesson.forEach { (group, lesson) ->
                item {
                    GroupListEntry(
                        horizontalPadding = horizontal,
                        group = group,
                        nextLesson = lesson,
                        timeZone = timeZone,
                        onClick = vm::groupOnClick
                    )
                }
                item {
                    Spacer(Modifier.height(100.dp))
                }
            }
        }
    }
}