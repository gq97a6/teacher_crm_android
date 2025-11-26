package org.labcluster.crm.android.screen.lesson.compose

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Topic
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import org.labcluster.crm.android.composable.PreviewScaffold
import org.labcluster.crm.android.cs
import org.labcluster.crm.android.dateFormat
import org.labcluster.crm.android.dayFormat
import org.labcluster.crm.android.timeFormat
import org.labcluster.crm.shared.Mock
import org.labcluster.crm.shared.model.Lesson
import org.labcluster.crm.shared.timeEnd
import org.labcluster.crm.shared.timeStart

@Preview
@Composable
private fun Preview() = PreviewScaffold(showAppBar = false) { LessonAppBar() }

@Composable
fun LessonAppBar(
    lesson: Lesson = Mock.lessons.random(),
    timeZone: TimeZone = TimeZone.of("Europe/Warsaw"),
    isLive: Boolean = false,
    onShowTopic: () -> Unit = {},
    onShowCourse: () -> Unit = {}
) {
    val infiniteTransition = rememberInfiniteTransition()
    val dotAlpha by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        )
    )

    var isMenuExpanded by remember { mutableStateOf(false) }

    //12.03.2025
    val title = remember { lesson.timeStart(timeZone).format(dateFormat) }

    //Friday - 10:00 - 11:35
    val subtitle = remember {
        buildString {
            lesson.timeStart(timeZone).run {
                append(format(dayFormat))
                append(" - ")
                append(format(timeFormat))
            }
            append(" - ")
            append(lesson.timeEnd(timeZone).format(timeFormat))
        }
    }

    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    title,
                    fontWeight = FontWeight.Black,
                    color = cs.tertiary
                )
                if (isLive) Box(
                    modifier = Modifier
                        .alpha(dotAlpha)
                        .padding(start = 8.dp)
                        .size(14.dp)
                        .clip(CircleShape)
                        .background(cs.error)

                )
            }
        },
        subtitle = {
            Text(
                subtitle,
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
                        text = { Text("Pokaż temat") },
                        onClick = {
                            isMenuExpanded = false
                            onShowTopic()
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Topic,
                                contentDescription = ""
                            )
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Pokaż kurs") },
                        onClick = {
                            isMenuExpanded = false
                            onShowCourse()
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Book,
                                contentDescription = ""
                            )
                        }
                    )
                }
            }
        }
    )
}