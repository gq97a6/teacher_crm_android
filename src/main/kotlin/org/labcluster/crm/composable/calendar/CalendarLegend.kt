package org.labcluster.crm.composable.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.labcluster.crm.PreviewSample
import org.labcluster.crm.cs

@Preview
@Composable
private fun Preview() = PreviewSample { CalendarLegend() }

@Composable
fun CalendarLegend() {
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