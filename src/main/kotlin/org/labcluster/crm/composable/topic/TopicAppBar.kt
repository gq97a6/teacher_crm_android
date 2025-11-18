package org.labcluster.crm.composable.topic

import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import org.labcluster.crm.composable.shared.PreviewScaffold
import org.labcluster.crm.cs

@Preview
@Composable
private fun Preview() = PreviewScaffold(showAppBar = false) { TopicAppBar() }

@Composable
fun TopicAppBar(
    title: String = "Introduction to Programming with Python and Kotlin",
    subtitle: String? = "10 tematów"
) {
    var isMenuExpanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(
                title,
                fontWeight = FontWeight.Black,
                color = cs.tertiary,
                maxLines = 1,
                overflow = TextOverflow.Clip,
                modifier = Modifier
                    .fillMaxWidth(.9f)
                    .basicMarquee(
                        spacing = MarqueeSpacing(10.dp),
                        velocity = 60.dp
                    )
            )
        },
        subtitle = {
            if (subtitle != null) Text(
                subtitle,
                fontWeight = FontWeight.Black,
                color = cs.primary
            )
        },
        actions = {
            Box(modifier = Modifier) {
                IconButton(onClick = {}) {
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
                        text = { Text("Opcja") },
                        onClick = {
                            isMenuExpanded = false
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Topic,
                                contentDescription = ""
                            )
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Opcja") },
                        onClick = {
                            isMenuExpanded = false
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