package org.labcluster.crm.android.screen.login.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.labcluster.crm.android.composable.PreviewScaffold
import org.labcluster.crm.android.cs

@Preview
@Composable
private fun Preview() = PreviewScaffold(showAppBar = false) { LoginScreen() }

@Composable
fun LoginScreen(
    isLoading: Boolean = false,
    onLogin: () -> Unit = {}
) {


    Column(
        Modifier
            .fillMaxSize()
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

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(140.dp)
                .padding(top = 10.dp)
        ) {
            if (isLoading) CircularWavyProgressIndicator()
            else OutlinedButton(
                onClick = onLogin,
                shape = ShapeDefaults.ExtraExtraLarge,
                contentPadding = PaddingValues(40.dp)
            ) {
                Text("Zaloguj się")
            }
        }

        //Button(
        //    onClick = onRegister,
        //    modifier = Modifier
        //        .fillMaxWidth(.4f)
        //        .padding(top = 10.dp)
        //) {
        //    Text("Rejestracja")
        //}
    }
}