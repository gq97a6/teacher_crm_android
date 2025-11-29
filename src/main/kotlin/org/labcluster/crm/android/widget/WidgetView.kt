package org.labcluster.crm.android.widget

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import org.labcluster.crm.android.composable.PreviewScaffold
import org.labcluster.crm.android.screen.calendar.CalendarViewModel

@Preview
@Composable
private fun Preview() = PreviewScaffold(false, doMock = true) { CalendarView() }

@Composable
fun BoxScope.CalendarView(vm: CalendarViewModel = viewModel()) {

    Text("dupa")
}