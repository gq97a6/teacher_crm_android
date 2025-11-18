package org.labcluster.crm.composable.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedSecureTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.labcluster.crm.PreviewSample
import org.labcluster.crm.composable.shared.WavyDivider
import org.labcluster.crm.cs

@Preview
@Composable
private fun Preview() = PreviewSample { SettingScreen() }

@Composable
fun SettingScreen(
    name: TextFieldState = TextFieldState("Mariusz"),
    surname: TextFieldState = TextFieldState("Wysocki"),
    contact1: TextFieldState = TextFieldState("Piła 64-920 Dabrowskiego 4"),
    contact2: TextFieldState = TextFieldState("Piła 64-920 Dabrowskiego 4"),
    phone: TextFieldState = TextFieldState("576 398 281"),
    pesel: TextFieldState = TextFieldState("95010759833"),
    bank: TextFieldState = TextFieldState("PL 36 9159 1023 8834 0255 4368 5287"),
    nip: TextFieldState = TextFieldState("PL1234567890"),
    currentPassword: TextFieldState = TextFieldState(""),
    newPassword1: TextFieldState = TextFieldState(""),
    newPassword2: TextFieldState = TextFieldState(""),
    onSaveClicked: () -> Unit = {},
    onChangePasswordClicked: () -> Unit = {},
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Dane", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = cs.primary)

            WavyDivider(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .height(10.dp),
                thickness = 3.dp,
                wavelength = 25.dp
            )
        }

        Spacer(Modifier.height(5.dp))

        OutlinedTextField(
            state = name,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Imię") }
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            state = surname,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nazwisko") }
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            state = contact1,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Adres") }
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            contact2,
            Modifier.fillMaxWidth(),
            label = { Text("Adres korespondencyjny") })

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            state = phone,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Numer telefonu") }
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            state = pesel,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("PESEL") }
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            state = bank,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Numer konta bankowego") }
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            state = nip,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("NIP") }
        )

        Spacer(Modifier.height(8.dp))

        ElevatedButton(
            onClick = onSaveClicked,
            modifier = Modifier.fillMaxWidth()
        ) { Text("Zapisz") }

        Spacer(Modifier.height(30.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Zmiana hasła", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = cs.primary)

            WavyDivider(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .height(10.dp),
                thickness = 3.dp,
                wavelength = 25.dp
            )
        }
        Spacer(Modifier.height(5.dp))

        OutlinedSecureTextField(
            state = currentPassword,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Obecne hasło") }
        )

        Spacer(Modifier.height(8.dp))

        OutlinedSecureTextField(
            state = newPassword1,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nowe hasło") }
        )

        Spacer(Modifier.height(8.dp))

        OutlinedSecureTextField(
            state = newPassword2,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nowe hasło") }
        )

        Spacer(Modifier.height(8.dp))

        ElevatedButton(onChangePasswordClicked, Modifier.fillMaxWidth()) { Text("Zmień hasło") }

        Spacer(Modifier.height(20.dp))
    }
}