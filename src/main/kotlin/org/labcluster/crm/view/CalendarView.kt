package org.labcluster.crm.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.labcluster.crm.PreviewSample
import org.labcluster.crm.composable.calendar.CalendarAppBar
import org.labcluster.crm.composable.calendar.CalendarContent
import org.labcluster.crm.composable.calendar.CalendarLegend
import org.labcluster.crm.composable.calendar.CalendarToolbar
import org.labcluster.crm.viewmodel.CalendarViewModel

@Preview
@Composable
private fun Preview() = PreviewSample { CalendarView() }

@Composable
fun CalendarView(vm: CalendarViewModel = viewModel()) {

    val lessons by vm.lessons.collectAsStateWithLifecycle()
    val isLegendShown by vm.isLegendShown.collectAsStateWithLifecycle()
    val timeZone by vm.state.chronos.timeZone.collectAsStateWithLifecycle()
    val title by vm.title.collectAsStateWithLifecycle()
    val subTitle by remember {
        derivedStateOf { lessons.count().toString() + " lekcji" }
    }

    BackHandler(isLegendShown, vm::onCloseLegend)

    Box(Modifier.fillMaxHeight()) {
        Column {
            CalendarAppBar(
                title = if (isLegendShown) "Legenda" else title,
                subtitle = if (isLegendShown) null else subTitle,
                showNavigationIcon = isLegendShown,
                onLegendClicked = vm::onLegendClicked,
                onNavigationClicked = vm::onCloseLegend
            )
            if (isLegendShown) CalendarLegend()
            else CalendarContent(
                lessons = lessons,
                timeZone = timeZone,
                onLessonClicked = vm::onLessonClicked
            )
        }
        if (!isLegendShown) CalendarToolbar(
            onPreviousClicked = vm::onPreviousClicked,
            onCurrentSelected = vm::onCurrentSelected,
            onNextClicked = vm::onNextClicked
        )
    }
}



