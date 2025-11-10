package org.labcluster.crm.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.outlined.Cloud
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material.icons.outlined.Topic
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.labcluster.crm.utils.PreviewSample
import org.labcluster.crm.utils.cs
import org.labcluster.crm.viewmodels.CalendarViewModel

@Preview
@Composable
private fun Preview1() = PreviewSample { CalendarScreen() }

@Preview
@Composable
private fun Preview2() = PreviewSample { CalendarSearchScreen1() }

@Preview
@Composable
private fun Preview3() = PreviewSample { CalendarScreenLegend() }

@Composable
fun CalendarScreen(vm: CalendarViewModel = viewModel()) {
    val appBarTitle by vm.appBarTitle.collectAsStateWithLifecycle()
    val appBarSubtitle by vm.appBarSubtitle.collectAsStateWithLifecycle()
    val calendar by vm.calendar.collectAsStateWithLifecycle()

    Box(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {
            TopAppBar(
                title = {
                    Text(
                        appBarTitle,
                        fontWeight = FontWeight.Black,
                        color = cs.tertiary
                    )
                },
                subtitle = {
                    Text(
                        appBarSubtitle,
                        fontWeight = FontWeight.Black,
                        color = cs.primary
                    )
                },
            )

            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 15.dp)
            ) {
                items(calendar.size) { index ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = calendar[index][0] + " ",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Black,
                            color = cs.primary
                        )
                        Text(
                            text = calendar[index][1],
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = cs.tertiary
                        )

                        HorizontalDivider(Modifier.padding(start = 5.dp))
                    }

                    FlowRow {
                        calendar[index].drop(2).forEach {
                            FilterChip(
                                selected = false,
                                onClick = { },
                                label = { Text(it) },
                                modifier = Modifier.padding(end = 10.dp)
                            )
                        }
                    }

                    Spacer(Modifier.height(20.dp))
                }

                item {
                    Spacer(Modifier.height(80.dp))
                }
            }
        }

        HorizontalFloatingToolbar(
            expanded = false,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp)
        ) {
            IconButton({}) {
                Icon(
                    Icons.Outlined.Topic, ""
                )
            }
            IconButton({}) {
                Icon(
                    Icons.Outlined.Edit, ""
                )
            }
            IconButton({}) {
                Icon(
                    Icons.Outlined.Delete, ""
                )
            }
            IconButton({}) {
                Icon(
                    Icons.Outlined.Error, ""
                )
            }
            IconButton({}) {
                Icon(
                    Icons.Outlined.Cloud, ""
                )
            }
        }
    }
}

@Composable
fun CalendarSearchScreen1(vm: CalendarViewModel = CalendarViewModel()) {
    val startDate by vm.startDate.collectAsStateWithLifecycle()
    val endDate by vm.endDate.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Kalendarz", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = cs.primary)

        Spacer(Modifier.height(10.dp))

        OutlinedTextField(
            startDate,
            Modifier.fillMaxWidth(.7f),
            label = { Text("Od") },
            trailingIcon = {
                Icon(
                    Icons.Filled.CalendarMonth, "", Modifier
                        .padding(20.dp)
                        .height(15.dp)
                )
            })

        Spacer(Modifier.height(10.dp))

        OutlinedTextField(
            endDate,
            Modifier.fillMaxWidth(.8f),
            label = { Text("Do") },
            trailingIcon = {
                Icon(
                    Icons.Filled.CalendarMonth, "", Modifier
                        .padding(20.dp)
                        .height(15.dp)
                )
            })

        Spacer(Modifier.height(20.dp))
        Button(vm::onSearchClick, Modifier.width(200.dp)) { Text("Szukaj") }
    }
}

@Composable
fun CalendarScreenLegend(vm: CalendarViewModel = CalendarViewModel()) {

    Column(
        Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Text("Legenda", fontSize = 40.sp, fontWeight = FontWeight.Black, color = cs.tertiary)
        HorizontalDivider(Modifier.padding(top = 5.dp))

        Spacer(Modifier.height(15.dp))

        Text(
            "Zaplanowana lekcja",
            fontSize = 20.sp,
            fontWeight = FontWeight.Black,
            color = cs.primary
        )
        FilterChip(false, { }, { Text("11:45 - 13:20") }, Modifier.padding(end = 10.dp))

        Spacer(Modifier.height(15.dp))

        Text(
            "Rozpoczęta lekcja",
            fontSize = 20.sp,
            fontWeight = FontWeight.Black,
            color = cs.primary
        )
        FilterChip(true, { }, { Text("11:45 - 13:20") }, Modifier.padding(end = 10.dp))

    }
}