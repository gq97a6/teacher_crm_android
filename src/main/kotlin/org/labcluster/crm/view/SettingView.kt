package org.labcluster.crm.view

import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import org.labcluster.crm.PreviewSample
import org.labcluster.crm.composable.SettingScreen
import org.labcluster.crm.viewmodel.SettingViewModel

@Preview
@Composable
private fun Preview() = PreviewSample { SettingView() }

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