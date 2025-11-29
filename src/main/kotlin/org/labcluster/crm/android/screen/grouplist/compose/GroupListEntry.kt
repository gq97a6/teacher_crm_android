package org.labcluster.crm.android.screen.grouplist.compose

import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.toLocalDateTime
import org.labcluster.crm.android.app.App
import org.labcluster.crm.android.composable.PreviewSimple
import org.labcluster.crm.android.cs
import org.labcluster.crm.android.mock.Mock
import org.labcluster.crm.android.polishDayOfWeekNames
import org.labcluster.crm.android.timeFormat
import org.labcluster.crm.shared.model.Group
import org.labcluster.crm.shared.model.Lesson
import kotlin.time.Instant

@Preview
@Composable
private fun Preview() = PreviewSimple { GroupListEntry() }

@Composable
fun GroupListEntry(
    horizontalPadding: Dp = 15.dp,
    group: Group = Mock.groups.random(),
    nextLesson: Lesson? = Mock.lessons.random(),
    timeZone: TimeZone = App.state.chronos.timeZone.value,
    onClick: (Group) -> Unit = {}
) {
    val dayText = remember { polishDayOfWeekNames.names[group.dayIndex] }
    val timeText = remember {
        Instant.fromEpochSeconds(group.timeEpoch)
            .toLocalDateTime(timeZone)
            .format(timeFormat)
    }

    Box(
        Modifier
            .fillMaxWidth()
            .height(110.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(top = 5.dp)
                .padding(vertical = 5.dp)
                .padding(horizontal = horizontalPadding)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = nextLesson?.course?.name ?: "Brak kursu grupy",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = cs.tertiary,
                modifier = Modifier.basicMarquee(
                    spacing = MarqueeSpacing(10.dp),
                    velocity = 60.dp
                )
            )
            Text(
                text = nextLesson?.topic?.name ?: "Brak tematu lekcji",
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp,
                color = cs.primary,
                modifier = Modifier.basicMarquee(
                    spacing = MarqueeSpacing(10.dp),
                    velocity = 60.dp
                )
            )

            Row {
                SuggestionChip(
                    {},
                    label = { Text(dayText) },
                )
                Spacer(Modifier.width(10.dp))
                SuggestionChip(
                    {},
                    label = { Text(timeText) },
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onClick(group) }
        )
    }
}