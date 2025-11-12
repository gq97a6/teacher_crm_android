package org.labcluster.crm.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.labcluster.crm.PreviewSample
import org.labcluster.crm.composable.calendar.CalendarScreen
import org.labcluster.crm.viewmodel.CalendarViewModel

@Preview
@Composable
private fun Preview() = PreviewSample { CalendarView() }

@Composable
fun CalendarView(vm: CalendarViewModel = viewModel()) {
    val appBarTitle by vm.appBarTitle.collectAsStateWithLifecycle()
    val appBarSubtitle by vm.appBarSubtitle.collectAsStateWithLifecycle()
    val calendar by vm.calendar.collectAsStateWithLifecycle()

    CalendarScreen(
        appBarTitle = appBarTitle,
        appBarSubtitle = appBarSubtitle,
        calendar = calendar
    )
}



