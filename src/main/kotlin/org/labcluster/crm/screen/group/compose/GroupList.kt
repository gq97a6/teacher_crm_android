package org.labcluster.crm.screen.group.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import kotlinx.datetime.TimeZone
import org.labcluster.crm.app.App
import org.labcluster.crm.composable.shared.PreviewScaffold
import org.labcluster.crm.shared.Mock
import org.labcluster.crm.shared.model.Group
import org.labcluster.crm.shared.model.Lesson

@Preview
@Composable
private fun Preview() = PreviewScaffold { GroupList(it) }

@Composable
fun GroupList(
    paddingValues: PaddingValues = PaddingValues(),
    groupsWithLessons: Map<Group, List<Lesson>> = Mock.groups.associateWith {
        Mock.lessons.shuffled().take(15)
    },
    timeZone: TimeZone = App.state.chronos.timeZone.value,
    groupOnClick: (Group) -> Unit = {}
) {
    val topPadding = remember { paddingValues.calculateTopPadding() }
    val horizontal = remember { paddingValues.calculateStartPadding(LayoutDirection.Ltr) }

    LazyColumn(Modifier.padding(top = topPadding)) {
        groupsWithLessons.forEach {
            item {
                GroupListEntry(
                    horizontalPadding = horizontal,
                    group = it.key,
                    lesson = it.value.first(),
                    timeZone = timeZone,
                    onClick = groupOnClick
                )
            }
        }
    }
}