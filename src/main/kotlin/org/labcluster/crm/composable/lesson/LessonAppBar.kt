package org.labcluster.crm.composable.lesson

import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.EaseOutElastic
import androidx.compose.animation.core.EaseOutExpo
import androidx.compose.animation.core.EaseOutQuad
import androidx.compose.animation.core.LinearEasing
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
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Topic
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import org.labcluster.crm.PreviewSample
import org.labcluster.crm.cs

@Preview
@Composable
private fun Preview() = PreviewSample { LessonAppBar() }

@Composable
fun LessonAppBar(
    title: String = "20.03.2025",
    subtitle: String = "Czwartek - 10:00 - 11:35",
    isMenuExpanded: Boolean = false,
    onDropdownMenuDismissed: () -> Unit = {},
    onMenuClicked: () -> Unit = {},
    onEditLesson: () -> Unit = {},
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

    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    title,
                    fontWeight = FontWeight.Black,
                    color = cs.tertiary
                )
                Box(
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
                IconButton(onClick = onMenuClicked) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More options",
                        tint = cs.primary
                    )
                }

                DropdownMenu(
                    expanded = isMenuExpanded,
                    onDismissRequest = onDropdownMenuDismissed,
                    offset = DpOffset(x = (-10).dp, y = 0.dp)
                ) {
                    DropdownMenuItem(
                        text = { Text("Edytuj lekcję") },
                        onClick = onEditLesson,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Edit,
                                contentDescription = ""
                            )
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Pokaż temat") },
                        onClick = onShowTopic,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Topic,
                                contentDescription = ""
                            )
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Pokaż kurs") },
                        onClick = onShowCourse,
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
            Icon(
                Icons.Filled.ArrowBackIosNew,
                contentDescription = "",
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        }
    )
}