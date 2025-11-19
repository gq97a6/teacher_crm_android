package org.labcluster.crm.screen.topic

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.labcluster.crm.composable.shared.PreviewScaffold
import org.labcluster.crm.screen.topic.compose.TopicAppBar
import org.labcluster.crm.screen.topic.compose.TopicContent

@Preview
@Composable
private fun Preview() = PreviewScaffold(false) { TopicView() }

@Composable
fun TopicView(vm: TopicViewModel = viewModel()) {

    val topic by vm.state.topic.collectAsState()

    Scaffold(
        modifier = Modifier,
        topBar = {
            TopicAppBar(title = topic.name)
        },
        contentWindowInsets = WindowInsets(left = 15.dp, right = 10.dp)
    ) { paddingValues ->
        TopicContent(paddingValues)
    }
}