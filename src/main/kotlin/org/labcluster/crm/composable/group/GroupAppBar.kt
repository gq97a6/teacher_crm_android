package org.labcluster.crm.composable.group

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Book
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import org.labcluster.crm.composable.shared.PreviewSample
import org.labcluster.crm.cs
import org.labcluster.crm.shared.Mock
import org.labcluster.crm.shared.model.Group

@Preview
@Composable
private fun Preview() = PreviewSample(showAppBar = false) { GroupAppBar() }

@Composable
fun GroupAppBar(group: Group = Mock.groups.random()) {

    var isMenuExpanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(
                "Grupy",
                fontWeight = FontWeight.Black,
                color = cs.tertiary
            )
        },
        subtitle = {
            //Text(
            //    "Subtitle",
            //    fontWeight = FontWeight.Black,
            //    color = cs.primary
            //)
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
                        text = { Text("Legenda") },
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
        },
        navigationIcon = {
            //IconButton({}) {
            //    Icon(
            //        Icons.Filled.ArrowBackIosNew,
            //        contentDescription = "",
            //        modifier = Modifier.padding(horizontal = 10.dp)
            //    )
            //}
        }
    )
}