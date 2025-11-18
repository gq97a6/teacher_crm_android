package org.labcluster.crm.view

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.labcluster.crm.composable.group.GroupAppBar
import org.labcluster.crm.composable.group.GroupScreen
import org.labcluster.crm.composable.shared.PreviewScaffold
import org.labcluster.crm.viewmodel.GroupViewModel

@Preview
@Composable
private fun Preview() = PreviewScaffold(false) { GroupView() }

@Composable
fun GroupView(vm: GroupViewModel = viewModel()) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            GroupAppBar()
        },
        contentWindowInsets = WindowInsets(left = 15.dp, right = 10.dp)
    ) { paddingValues ->
        GroupScreen(paddingValues)
    }
}