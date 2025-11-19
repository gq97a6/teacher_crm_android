package org.labcluster.crm.screen.group.compose

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.labcluster.crm.composable.shared.PreviewScaffold
import org.labcluster.crm.cs
import org.labcluster.crm.shared.Mock

@Preview
@Composable
private fun Preview() = PreviewScaffold { GroupContent(it) }

@Composable
fun GroupContent(paddingValues: PaddingValues = PaddingValues()) {
    Column(Modifier.padding(paddingValues)) {
        Row {
            FilterChip(
                true,
                onClick = {},
                label = {
                    Text("Uczniowie")
                }
            )
            Spacer(Modifier.width(10.dp))
            FilterChip(
                false,
                onClick = {},
                label = {
                    Text("Lekcje")
                }
            )
        }

        Spacer(Modifier.height(5.dp))

        LazyColumn {
            items(Mock.students) { student ->
                Row(
                    modifier = Modifier
                        .heightIn(72.dp)
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .border(1.dp, cs.outline, shape = RoundedCornerShape(5.dp))
                        .padding(15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FlowRow(Modifier.weight(1f)) {
                        student.name.split(" ").forEach { name ->
                            Text(
                                "$name ",
                                fontSize = 20.sp,
                                color = cs.onBackground,
                                lineHeight = 20.sp,
                                fontWeight = FontWeight.Black,
                                modifier = Modifier
                            )
                        }
                        student.surname.split(" ").forEach { name ->
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
                }
            }
        }
    }
}