package org.labcluster.crm.android.screen.setting

import androidx.compose.foundation.layout.WindowInsets
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
import org.labcluster.crm.android.composable.PreviewScaffold
import org.labcluster.crm.android.cs
import org.labcluster.crm.android.screen.setting.compose.SettingScreen

@Preview
@Composable
private fun Preview() = PreviewScaffold(false) { SettingView() }

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
        },
        contentWindowInsets = WindowInsets(left = 15.dp, right = 15.dp)
    ) { paddingValues ->
        SettingScreen(
            paddingValues = paddingValues,
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