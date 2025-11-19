package org.labcluster.crm.screen.calendar.compose

import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import org.labcluster.crm.app.App
import org.labcluster.crm.composable.shared.PreviewScaffold
import org.labcluster.crm.cs
import org.labcluster.crm.dayFormat
import org.labcluster.crm.shared.Mock
import org.labcluster.crm.shared.model.Lesson
import org.labcluster.crm.shared.timeEnd
import org.labcluster.crm.shared.timeStart
import org.labcluster.crm.shortDateFormat
import org.labcluster.crm.timeFormat

@Preview
@Composable
private fun Preview() = PreviewScaffold { CalendarContent(it) }

@Composable
fun CalendarContent(
    paddingValues: PaddingValues = PaddingValues(),
    lessons: List<Lesson> = Mock.lessons,
    timeZone: TimeZone = App.state.chronos.timeZone.value,
    onLessonClicked: (Lesson) -> Unit = {},
) {
    val lessons = remember(lessons) {
        lessons.groupBy {
            it.timeStart(timeZone)
        }
    }

    LazyColumn(Modifier
        .fillMaxSize()
        .padding(paddingValues)) {
        lessons.forEach { (time, lessons) ->
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 5.dp)
                ) {
                    Text(
                        text = time.format(shortDateFormat),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Black,
                        color = cs.primary
                    )
                    Spacer(Modifier.width(5.dp))
                    Text(
                        text = time.format(dayFormat),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = cs.tertiary
                    )

                    HorizontalDivider(Modifier.padding(start = 5.dp))
                }

                FlowRow {
                    lessons.forEach { lesson ->
                        val startTime = lesson.timeStart(timeZone).format(timeFormat)
                        val endTime = lesson.timeEnd(timeZone).format(timeFormat)

                        OutlinedButton(
                            onClick = { onLessonClicked(lesson) },
                            modifier = Modifier.padding(end = 10.dp)
                        ) { Text("$startTime - $endTime") }
                    }
                }

                Spacer(Modifier.height(20.dp))
            }
        }

        item {
            Spacer(Modifier.height(80.dp))
        }
    }
}