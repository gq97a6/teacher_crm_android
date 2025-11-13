package org.labcluster.crm.composable.lesson

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
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
    isMenuExpanded: Boolean = false,
    onDropdownMenuDismissed: () -> Unit = {},
    onMenuClicked: () -> Unit = {},
    onEditLesson: () -> Unit = {},
    onShowTopic: () -> Unit = {},
    onShowCourse: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                "20.03.2025",
                fontWeight = FontWeight.Black,
                color = cs.tertiary
            )
        },
        subtitle = {
            Text(
                "Czwartek - 10:00 - 11:35",
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