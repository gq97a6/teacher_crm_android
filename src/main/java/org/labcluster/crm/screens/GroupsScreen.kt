package org.labcluster.crm.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import org.labcluster.crm.utils.PreviewSample
import org.labcluster.crm.viewmodels.GroupsViewModel

@Preview
@Composable
private fun Preview() = PreviewSample { GroupsScreen() }

@Composable
fun GroupsScreen(vm: GroupsViewModel = viewModel()) {
}