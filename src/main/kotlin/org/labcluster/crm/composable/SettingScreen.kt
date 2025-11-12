package org.labcluster.crm.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun SettingScreen() {

    val name = rememberTextFieldState("Mariusz")
    val surname = rememberTextFieldState("Wysocki")
    val contact1 = rememberTextFieldState("Piła 64-920 Dabrowskiego 4")
    val phone = rememberTextFieldState("576 398 281")
    val pesel = rememberTextFieldState("95010759833")
    val bank = rememberTextFieldState("PL 36 9159 1023 8834 0255 4368 5287")
    val nip = rememberTextFieldState("8232155393")
    val contact2 = rememberTextFieldState("Piła 64-920 Dabrowskiego 4")

    Column(
        Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(20.dp))

        Text("Dane", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = cs.primary)
        WavyDivider(
            modifier = Modifier
                .padding(top = 1.dp)
                .padding(bottom = 10.dp)
                .height(5.dp),
            thickness = 1.5.dp
        )

        OutlinedTextField(name, Modifier.fillMaxWidth(), label = { Text("Imię") })

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(surname, Modifier.fillMaxWidth(), label = { Text("Nazwisko") })

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(contact1, Modifier.fillMaxWidth(), label = { Text("Adres") })

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(phone, Modifier.fillMaxWidth(), label = { Text("Numer telefonu") })

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(pesel, Modifier.fillMaxWidth(), label = { Text("PESEL") })

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(bank, Modifier.fillMaxWidth(), label = { Text("Numer konta bankowego") })

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(nip, Modifier.fillMaxWidth(), label = { Text("NIP") })

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            contact2,
            Modifier.fillMaxWidth(),
            label = { Text("Adres korespondencyjny") })

        Spacer(Modifier.height(8.dp))

        ElevatedButton({}, Modifier.fillMaxWidth()) { Text("Zapisz") }

        Spacer(Modifier.height(15.dp))
        Text("Zmiana hasła", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = cs.primary)
        WavyDivider(
            modifier = Modifier
                .padding(top = 1.dp)
                .padding(bottom = 10.dp)
                .height(5.dp),
            thickness = 1.5.dp
        )

        OutlinedTextField(
            rememberTextFieldState(""),
            Modifier.fillMaxWidth(),
            label = { Text("Obecne hasło") })

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            rememberTextFieldState(""),
            Modifier.fillMaxWidth(),
            label = { Text("Nowe hasło") })

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            rememberTextFieldState(""),
            Modifier.fillMaxWidth(),
            label = { Text("Nowe hasło") })

        Spacer(Modifier.height(8.dp))

        ElevatedButton({}, Modifier.fillMaxWidth()) { Text("Zmień hasło") }

        Spacer(Modifier.height(20.dp))
    }
}