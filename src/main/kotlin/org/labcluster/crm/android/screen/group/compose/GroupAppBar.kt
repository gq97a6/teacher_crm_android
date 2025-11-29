package org.labcluster.crm.android.screen.group.compose

import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Fingerprint
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.Clipboard
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.toLocalDateTime
import org.labcluster.crm.android.app.App
import org.labcluster.crm.android.composable.PreviewScaffold
import org.labcluster.crm.android.cs
import org.labcluster.crm.android.polishDayOfWeekNames
import org.labcluster.crm.android.timeFormat
import org.labcluster.crm.shared.Mock
import org.labcluster.crm.shared.model.Group
import org.labcluster.crm.shared.model.Lesson
import kotlin.time.Instant

@Preview
@Composable
private fun Preview() = PreviewScaffold(showAppBar = false) { GroupAppBar() }

@Composable
fun GroupAppBar(
    group: Group = Mock.groups.random(),
    nextLesson: Lesson = Mock.lessons.random(),
    timeZone: TimeZone = App.state.chronos.timeZone.value,
    onCopyUuid: (Clipboard) -> Unit = {}
) {
    val subTitle = remember {
        buildString {
            append(polishDayOfWeekNames.names[group.dayIndex.toInt()])
            append(" - ")
            Instant.fromEpochSeconds(group.timeEpoch)
                .toLocalDateTime(timeZone)
                .format(timeFormat)
                .let { append(it) }
        }
    }

    var isMenuExpanded by remember { mutableStateOf(false) }
    val clipboard = LocalClipboard.current

    TopAppBar(
        title = {
            Text(
                nextLesson.course?.name ?: "Brak kursu grupy",
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
        actions = {
            Box(modifier = Modifier) {
                IconButton(onClick = { isMenuExpanded = !isMenuExpanded }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More options",
                        tint = cs.primary
                    )
                }

                DropdownMenu(
                    expanded = isMenuExpanded,
                    onDismissRequest = { isMenuExpanded = false },
                    offset = DpOffset(x = (-10).dp, y = 0.dp)
                ) {
                    DropdownMenuItem(
                        text = { Text("Kopiuj UUID") },
                        onClick = {
                            isMenuExpanded = false
                            onCopyUuid(clipboard)
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Fingerprint,
                                contentDescription = ""
                            )
                        }
                    )
                }
            }
        }
    )
}