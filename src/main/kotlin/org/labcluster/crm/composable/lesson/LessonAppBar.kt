package org.labcluster.crm.composable.lesson

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
import androidx.compose.runtime.derivedStateOf
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
import org.labcluster.crm.PreviewSample
import org.labcluster.crm.cs
import org.labcluster.crm.dateFormat
import org.labcluster.crm.shared.Mock
import org.labcluster.crm.shared.epochEnd
import org.labcluster.crm.shared.model.Lesson
import org.labcluster.crm.shared.timeEnd
import org.labcluster.crm.shared.timeStart
import org.labcluster.crm.timeFormat
import kotlin.time.Clock

@Preview
@Composable
private fun Preview() = PreviewSample { LessonAppBar() }

@Composable
fun LessonAppBar(
    lesson: Lesson = Mock.lessons.random(),
    timeZone: TimeZone = TimeZone.of("Europe/Warsaw"),
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

    val isLive = remember {
        Clock.System.now().epochSeconds in lesson.epochStart..lesson.epochEnd()
    }
    var isMenuExpanded by remember { mutableStateOf(false) }

    //12.03.2025
    val title by remember {
        derivedStateOf {
            lesson.timeStart(timeZone).format(dateFormat)
        }
    }

    //Czwartek - 10:00 - 11:35
    val subtitle by remember {
        derivedStateOf {
            buildString {
                append("Czwartek")
                append(" - ")
                append(lesson.timeStart(timeZone).format(timeFormat))
                append(" - ")
                append(lesson.timeEnd(timeZone).format(timeFormat))
            }
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
        },
        navigationIcon = {
            //Icon(
            //    Icons.Filled.ArrowBackIosNew,
            //    contentDescription = "",
            //    modifier = Modifier.padding(horizontal = 10.dp)
            //)
        }
    )
}