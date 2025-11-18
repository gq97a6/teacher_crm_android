package org.labcluster.crm.composable.group

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.InputChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.labcluster.crm.composable.shared.PreviewScaffold
import org.labcluster.crm.cs
import org.labcluster.crm.shared.Mock
import org.labcluster.crm.shared.model.Group

@Preview
@Composable
private fun Preview() = PreviewScaffold { GroupScreen(it) }

@Composable
fun GroupScreen(
    paddingValues: PaddingValues = PaddingValues(),
    group: Group = Mock.groups.random()
) {
    Column {

    }
    Column(
        Modifier
            .fillMaxWidth()
            .background(Color.Red)
            .padding(paddingValues)
            .clickable {

            }
    ) {
        Text(
            text = "Podstawy programowania",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = cs.tertiary
        )
        Text(
            text = "Czwartek • 18:00 - 20:00 • Online",
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            color = cs.primary
        )

        Row {
            InputChip(
                selected = false,
                onClick = { },
                label = { Text("Czwartek") }
            )
            Spacer(Modifier.width(10.dp))
            InputChip(
                selected = false,
                onClick = { },
                label = { Text("18:00 - 20:00") }
            )
            Spacer(Modifier.width(10.dp))
            InputChip(
                selected = false,
                onClick = { },
                label = { Text("Online") }
            )
        }
    }
}