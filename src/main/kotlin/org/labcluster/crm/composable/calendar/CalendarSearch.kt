package org.labcluster.crm.composable.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
private fun Preview() = PreviewSample { CalendarSearch() }

@Composable
fun CalendarSearch(
    startDate: TextFieldState = TextFieldState(),
    endDate: TextFieldState = TextFieldState(),
    onSearchClick: () -> Unit = {}
) {
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
        Button(onSearchClick, Modifier.width(200.dp)) { Text("Szukaj") }
    }
}