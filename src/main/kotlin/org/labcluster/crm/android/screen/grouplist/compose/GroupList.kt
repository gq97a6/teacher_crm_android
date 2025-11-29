package org.labcluster.crm.android.screen.grouplist.compose

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
import org.labcluster.crm.android.app.App
import org.labcluster.crm.android.composable.PreviewScaffold
import org.labcluster.crm.android.mock.Mock
import org.labcluster.crm.shared.model.Group
import org.labcluster.crm.shared.model.Lesson

@Preview
@Composable
private fun Preview() = PreviewScaffold(false) { GroupList(it) }

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
                    nextLesson = it.value.firstOrNull { it.epochBegin == null } ?: Lesson(),
                    timeZone = timeZone,
                    onClick = groupOnClick
                )
            }
        }
    }
}