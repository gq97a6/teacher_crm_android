package org.labcluster.crm.screen.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import org.labcluster.crm.composable.PreviewScaffold
import org.labcluster.crm.screen.login.compose.LoginScreen

@Preview
@Composable
private fun Preview() = PreviewScaffold(false) { LoginView() }

@Composable
fun LoginView(vm: LoginViewModel = viewModel()) {
    LoginScreen(vm::onLogin, vm::onRegister)
}