package org.labcluster.crm.composable.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
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
private fun Preview() = PreviewSample {
    Column {
        CalendarAppBar(
            title = "Legenda",
            subtitle = null,
            showNavigationIcon = true,
            onLegendClicked = { }
        )
        CalendarLegend()
    }
}

@Composable
fun CalendarLegend() {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Text(
            "Zaplanowana lekcja",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = cs.primary
        )

        Spacer(Modifier.height(5.dp))
        OutlinedButton({}) { Text("11:45 - 13:20") }
        Spacer(Modifier.height(15.dp))

        Text(
            "Rozpoczęta lekcja",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = cs.primary
        )

        Spacer(Modifier.height(5.dp))
        FilledTonalButton({}) { Text("11:45 - 13:20") }
        Spacer(Modifier.height(15.dp))

        Text(
            "Trwająca lekcja",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = cs.primary
        )

        Spacer(Modifier.height(5.dp))
        Button({}) { Text("11:45 - 13:20") }
    }
}