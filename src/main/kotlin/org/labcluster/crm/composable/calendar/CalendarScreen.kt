package org.labcluster.crm.composable.calendar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cloud
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material.icons.outlined.Topic
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.labcluster.crm.PreviewSample
import org.labcluster.crm.cs

@Composable
fun CatHead() {

}

@Composable
fun CatBody() {

}

@Composable
fun CatLegs() {
}

@Composable
fun Cat() {
    CatLegs()
    CatBody()
    CatHead()
}


@Preview
@Composable
private fun Preview() = PreviewSample { CalendarScreen() }

@Composable
fun CalendarScreen(
    appBarTitle: String = "Marzec | Kwiecień",
    appBarSubtitle: String = "Marzec | Kwiecień",
    calendar: List<List<String>> = listOf()
) {
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