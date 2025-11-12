package org.labcluster.crm.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import org.labcluster.crm.PreviewSample
import org.labcluster.crm.viewmodel.SettingViewModel

@Preview
@Composable
private fun Preview() = PreviewSample { SettingView() }

@Composable
fun SettingView(vm: SettingViewModel = viewModel()) {
}