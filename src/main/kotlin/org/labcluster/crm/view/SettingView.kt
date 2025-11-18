package org.labcluster.crm.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.labcluster.crm.composable.setting.SettingScreen
import org.labcluster.crm.composable.shared.PreviewSample
import org.labcluster.crm.cs
import org.labcluster.crm.viewmodel.SettingViewModel

@Preview
@Composable
private fun Preview() = PreviewSample(false) { SettingView() }

@Composable
fun SettingView(vm: SettingViewModel = viewModel()) {

    val name = rememberTextFieldState("Mariusz")
    val surname = rememberTextFieldState("Wysocki")
    val contact1 = rememberTextFieldState("Piła 64-920 Dabrowskiego 4")
    val contact2 = rememberTextFieldState("Piła 64-920 Dabrowskiego 4")
    val phone = rememberTextFieldState("576 398 281")
    val pesel = rememberTextFieldState("95010759833")
    val bank = rememberTextFieldState("PL 36 9159 1023 8834 0255 4368 5287")
    val nip = rememberTextFieldState("PL1234567890")

    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Ustawienia",
                        fontWeight = FontWeight.Black,
                        color = cs.tertiary
                    )
                }
            )
        }
    ) { paddingValues ->
        Box(
            Modifier
                .padding(paddingValues)
                .padding(horizontal = 15.dp)
        ) {
            SettingScreen(
                name = name,
                surname = surname,
                contact1 = contact1,
                contact2 = contact2,
                phone = phone,
                pesel = pesel,
                bank = bank,
                nip = nip,
                onSaveClicked = vm::onSavedClicked,
                onChangePasswordClicked = vm::onChangePasswordClicked,
            )
        }
    }
}