package org.labcluster.crm.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import org.labcluster.crm.utils.PreviewSample
import org.labcluster.crm.viewmodels.ReportViewModel

@Preview
@Composable
private fun Preview() = PreviewSample { ReportScreen() }

@Composable
fun ReportScreen(vm: ReportViewModel = viewModel()) {
}