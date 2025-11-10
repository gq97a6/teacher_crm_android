package org.labcluster.crm.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.material.icons.outlined.Topic
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.labcluster.crm.ui.components.DropList
import org.labcluster.crm.ui.components.WavyDivider
import org.labcluster.crm.utils.PreviewSample
import org.labcluster.crm.utils.cs
import org.labcluster.crm.viewmodels.LessonViewModel

@Preview
@Composable
private fun Preview() = PreviewSample { LessonScreen() }

@Composable
fun LessonScreen(vm: LessonViewModel = viewModel()) {
    val students by vm.students.collectAsStateWithLifecycle()
    val hasBegun by vm.hasBegun.collectAsStateWithLifecycle()
    val topic by vm.topic.collectAsStateWithLifecycle()
    val topics by vm.topics.collectAsStateWithLifecycle()

    Box(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {
            LessonScreenAppBar(vm)

            LazyColumn(
                Modifier
                    .weight(1f)
                    .padding(horizontal = 15.dp)
            ) {
                item {
                    OutlinedTextField(
                        state = rememberTextFieldState("Wprowadzenie do programowania"),
                        label = { Text("Kurs") },
                        readOnly = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                item {
                    DropList(
                        value = topic,
                        items = topics.map { it.name },
                        label = "Temat",
                        height = 400.dp,
                        onValueSet = vm::onSetTopic,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp)
                    )
                }

                item {
                    OutlinedTextField(
                        state = rememberTextFieldState("Karolina Wójcik"),
                        label = { Text("Prowadzący") },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp)
                    )
                }

                item {
                    WavyDivider(
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .padding(bottom = 10.dp)
                            .height(10.dp),
                        thickness = 3.dp,
                        wavelength = 25.dp
                    )
                }

                items(students.size) { index ->
                    Row(
                        Modifier
                            .heightIn(70.dp)
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                            .border(1.dp, cs.outline, shape = RoundedCornerShape(5.dp))
                            .clickable(true, onClick = {})
                            .padding(15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        FlowRow(Modifier.weight(1f)) {
                            students[index].name.split(" ").forEachIndexed { index, name ->
                                Text(
                                    "$name ",
                                    fontSize = 20.sp,
                                    color = cs.onBackground,
                                    lineHeight = 20.sp,
                                    fontWeight = FontWeight.Black,
                                    modifier = Modifier
                                )
                            }
                            students[index].surname.split(" ").forEachIndexed { index, name ->
                                Text(
                                    "$name ",
                                    fontSize = 20.sp,
                                    color = cs.onBackground,
                                    lineHeight = 20.sp,
                                    fontWeight = FontWeight.Normal,
                                    modifier = Modifier
                                )
                            }
                        }

                        Spacer(Modifier.width(25.dp))

                        if (hasBegun) CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides Dp.Unspecified) {
                            TriStateCheckbox(state = ToggleableState.On, {}, enabled = true)
                        }
                    }
                }

                item {
                    Spacer(Modifier.height(100.dp))
                }
            }
        }

        LessonScreenToolbar(vm)
    }
}

@Composable
private fun BoxScope.LessonScreenToolbar(vm: LessonViewModel) {
    val hasBegun by vm.hasBegun.collectAsStateWithLifecycle()

    HorizontalFloatingToolbar(
        expanded = false,
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 30.dp)
    ) {
        TextButton({}) {
            Icon(
                imageVector = if (hasBegun) Icons.Outlined.Edit
                else Icons.Outlined.PlayCircle,
                contentDescription = ""
            )
            Spacer(Modifier.width(10.dp))
            Text(
                if (hasBegun) "Edytuj" else "Start",
                modifier = Modifier
            )
        }
        Spacer(Modifier.width(5.dp))
        TextButton({}) {
            Icon(
                Icons.Outlined.Topic, ""
            )
            Spacer(Modifier.width(10.dp))
            Text(
                "Temat",
                modifier = Modifier
            )
        }
        Spacer(Modifier.width(5.dp))
        TextButton({}) {
            Icon(
                Icons.Outlined.Book, ""
            )
            Spacer(Modifier.width(10.dp))
            Text(
                "Kurs",
                modifier = Modifier
            )
        }
    }
}

@Composable
private fun LessonScreenAppBar(vm: LessonViewModel) {
    val isMenuExpanded by vm.isMenuExpanded.collectAsStateWithLifecycle()

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
                IconButton(onClick = vm::onMenuClicked) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More options",
                        tint = cs.primary
                    )
                }

                DropdownMenu(
                    expanded = isMenuExpanded,
                    onDismissRequest = vm::onDropdownMenuDismissed,
                    offset = DpOffset(x = (-10).dp, y = 0.dp)
                ) {
                    DropdownMenuItem(
                        text = { Text("Edytuj lekcję") },
                        onClick = { },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Edit,
                                contentDescription = ""
                            )
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Pokaż temat") },
                        onClick = { },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Topic,
                                contentDescription = ""
                            )
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Pokaż kurs") },
                        onClick = { },
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