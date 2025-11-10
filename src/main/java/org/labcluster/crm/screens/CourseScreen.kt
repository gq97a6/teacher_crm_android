package org.labcluster.crm.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.MoreVert
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.labcluster.crm.utils.PreviewSample
import org.labcluster.crm.utils.cs
import org.labcluster.crm.viewmodels.CourseViewModel

@Preview
@Composable
private fun Preview() = PreviewSample { CourseScreen() }

@Composable
fun CourseScreen(vm: CourseViewModel = viewModel()) {

    var isMenuExpanded by remember { mutableStateOf(false) }

    Column(Modifier.fillMaxSize()) {
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
                // Placeholder list of 100 strings for demonstration
                val menuItemData = List(10) { "Option ${it + 1}" }

                Box(
                    modifier = Modifier.padding(16.dp)
                ) {
                    IconButton(onClick = { isMenuExpanded = !isMenuExpanded }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "More options",
                            tint = cs.primary
                        )
                    }
                    DropdownMenu(
                        expanded = isMenuExpanded,
                        onDismissRequest = { isMenuExpanded = false }
                    ) {
                        menuItemData.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option) },
                                onClick = { /* Do something... */ }
                            )
                        }
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
}