package org.labcluster.crm.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import org.labcluster.crm.PreviewSample
import org.labcluster.crm.viewmodel.CalendarViewModel

@Preview
@Composable
private fun Preview() = PreviewSample { CalendarView() }

@Composable
fun CalendarView(vm: CalendarViewModel = viewModel()) {

}



