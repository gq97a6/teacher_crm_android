package org.labcluster.crm.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import org.labcluster.crm.PreviewSample
import org.labcluster.crm.viewmodel.TopicViewModel

@Preview
@Composable
private fun Preview() = PreviewSample { TopicView() }

@Composable
fun TopicView(vm: TopicViewModel = viewModel()) {
}