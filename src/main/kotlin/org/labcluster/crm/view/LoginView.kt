package org.labcluster.crm.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import org.labcluster.crm.composable.login.LoginScreen
import org.labcluster.crm.composable.shared.PreviewScaffold
import org.labcluster.crm.viewmodel.LoginViewModel

@Preview
@Composable
private fun Preview() = PreviewScaffold(false) { LoginView() }

@Composable
fun LoginView(vm: LoginViewModel = viewModel()) {
    LoginScreen(vm::onLogin, vm::onRegister)
}