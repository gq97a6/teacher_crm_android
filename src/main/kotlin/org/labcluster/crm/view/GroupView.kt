package org.labcluster.crm.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import org.labcluster.crm.composable.shared.PreviewSample
import org.labcluster.crm.viewmodel.GroupViewModel

@Preview
@Composable
private fun Preview() = PreviewSample(false) { GroupView() }

@Composable
fun GroupView(vm: GroupViewModel = viewModel()) {
}