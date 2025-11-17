package org.labcluster.crm.view

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import org.labcluster.crm.PreviewSample
import org.labcluster.crm.composable.topic.TopicAppBar
import org.labcluster.crm.composable.topic.TopicContent
import org.labcluster.crm.viewmodel.TopicViewModel

@Preview
@Composable
private fun Preview() = PreviewSample { TopicView() }

@Composable
fun TopicView(vm: TopicViewModel = viewModel()) {

    val topic by vm.state.topic.collectAsState()

    Column {
        TopicAppBar(title = topic.name)
        TopicContent()
    }
}