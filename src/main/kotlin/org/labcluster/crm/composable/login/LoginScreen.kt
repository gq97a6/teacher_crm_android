package org.labcluster.crm.composable.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.labcluster.crm.PreviewSample
import org.labcluster.crm.cs

@Preview
@Composable
private fun Preview() = PreviewSample { LoginScreen() }

@Composable
fun LoginScreen(
    onLogin: () -> Unit = {},
    onRegister: () -> Unit = {}
) {
    Column {
        Column(
            Modifier
                .fillMaxSize()
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Lorem",
                fontSize = 85.sp,
                fontWeight = FontWeight.Black,
                color = cs.primary,
                modifier = Modifier
            )
            Text(
                "Ipsum",
                fontSize = 85.sp,
                fontWeight = FontWeight.Bold,
                color = cs.tertiary,
                modifier = Modifier.offset(y = (-20).dp)
            )

            OutlinedButton(
                onClick = onLogin,
                modifier = Modifier
                    .fillMaxWidth(.6f)
                    .padding(top = 10.dp)
            ) {
                Text("Zaloguj się")
            }

            Button(
                onClick = onRegister,
                modifier = Modifier
                    .fillMaxWidth(.4f)
                    .padding(top = 10.dp)
            ) {
                Text("Rejestracja")
            }
        }
    }
}