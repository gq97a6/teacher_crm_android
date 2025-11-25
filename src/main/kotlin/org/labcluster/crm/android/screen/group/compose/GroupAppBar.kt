package org.labcluster.crm.android.screen.group.compose

import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.toLocalDateTime
import org.labcluster.crm.android.app.App
import org.labcluster.crm.android.composable.PreviewScaffold
import org.labcluster.crm.android.cs
import org.labcluster.crm.android.polishDayOfWeekNames
import org.labcluster.crm.shared.Mock
import org.labcluster.crm.shared.model.Group
import org.labcluster.crm.shared.model.Lesson
import org.labcluster.crm.android.timeFormat
import kotlin.time.Instant

@Preview
@Composable
private fun Preview() = PreviewScaffold(showAppBar = false) { GroupAppBar() }

@Composable
fun GroupAppBar(
    group: Group = Mock.groups.random(),
    nextLesson: Lesson = Mock.lessons.random(),
    timeZone: TimeZone = App.state.chronos.timeZone.value,
    onBackClick: () -> Unit = {}
) {
    val subTitle = remember {
        buildString {
            append(polishDayOfWeekNames.names[group.dayIndex.toInt()])
            append(" - ")
            Instant.fromEpochSeconds(group.epoch)
                .toLocalDateTime(timeZone)
                .format(timeFormat)
                .let { append(it) }
        }
    }

    TopAppBar(
        title = {
            Text(
                nextLesson.course?.name ?: "Nie znaleziono kursu",
                fontWeight = FontWeight.Black,
                color = cs.tertiary,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth(.97f)
                    .basicMarquee(
                        spacing = MarqueeSpacing(10.dp),
                        velocity = 60.dp
                    )
            )
        },
        subtitle = {
            Text(
                subTitle,
                fontWeight = FontWeight.Black,
                color = cs.primary
            )
        },
        navigationIcon = {
            IconButton(onBackClick) {
                Icon(
                    Icons.Filled.ArrowBackIosNew,
                    contentDescription = "",
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
            }
        }
    )
}