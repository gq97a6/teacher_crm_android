package org.labcluster.crm.screen.group.compose

import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import org.labcluster.crm.app.App
import org.labcluster.crm.composable.PreviewSimple
import org.labcluster.crm.cs
import org.labcluster.crm.dateFormat
import org.labcluster.crm.dayFormat
import org.labcluster.crm.shared.Mock
import org.labcluster.crm.shared.model.Lesson
import org.labcluster.crm.shared.timeEnd
import org.labcluster.crm.shared.timeStart
import org.labcluster.crm.timeFormat

@Preview
@Composable
private fun Preview() = PreviewSimple { GroupLessonListEntry() }

@Composable
fun GroupLessonListEntry(
    horizontalPadding: Dp = 15.dp,
    lesson: Lesson = Mock.lessons.random(),
    timeZone: TimeZone = App.state.chronos.timeZone.value,
    onClick: (Lesson) -> Unit = {}
) {
    val dayText = remember {
        lesson.timeStart(timeZone).format(dayFormat)
    }

    val timeText = remember {
        buildString {
            append(lesson.timeStart(timeZone).format(timeFormat))
            append(" - ")
            append(lesson.timeEnd(timeZone).format(timeFormat))
        }
    }

    val dateText = remember {
        lesson.timeStart(timeZone).format(dateFormat)
    }

    Box {
        Column(
            modifier = Modifier
                .padding(top = 5.dp)
                .padding(vertical = 5.dp)
                .padding(horizontal = horizontalPadding)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = lesson.topic?.name ?: "Brak tematu lekcji",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = cs.tertiary,
                modifier = Modifier
                    .fillMaxWidth(.97f)
                    .basicMarquee(
                        spacing = MarqueeSpacing(10.dp),
                        velocity = 60.dp
                    )
            )
            Text(
                text = dateText,
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp,
                color = cs.primary
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
                .fillMaxWidth()
                .height(80.dp)
                .clickable { onClick(lesson) }
        )
    }
}