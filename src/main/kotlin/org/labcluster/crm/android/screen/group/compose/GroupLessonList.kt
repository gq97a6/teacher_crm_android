package org.labcluster.crm.android.screen.group.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlinx.datetime.TimeZone
import org.labcluster.crm.android.app.App
import org.labcluster.crm.android.composable.PreviewScaffold
import org.labcluster.crm.android.mock.Mock
import org.labcluster.crm.shared.model.Lesson

@Preview
@Composable
private fun Preview() = PreviewScaffold(false) { GroupLessonList(it) }

@Composable
fun GroupLessonList(
    paddingValues: PaddingValues = PaddingValues(),
    lessons: List<Lesson> = Mock.lessons,
    timeZone: TimeZone = App.state.chronos.timeZone.value,
    lessonOnClick: (Lesson) -> Unit = {}
) {
    val horizontal = remember { paddingValues.calculateStartPadding(LayoutDirection.Ltr) }

    LazyColumn(Modifier.padding(top = 10.dp)) {
        lessons.forEach {
            item {
                GroupLessonListEntry(
                    horizontalPadding = horizontal,
                    lesson = it,
                    timeZone = timeZone,
                    onClick = lessonOnClick
                )
            }
        }
        item {
            Spacer(Modifier.height(100.dp))
        }
    }
}