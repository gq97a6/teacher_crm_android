package org.labcluster.crm.android.screen.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.labcluster.crm.android.composable.PreviewScaffold
import org.labcluster.crm.android.screen.login.compose.LoginScreen

@Preview
@Composable
private fun Preview() = PreviewScaffold(false) { LoginView() }

@Composable
fun LoginView(vm: LoginViewModel = viewModel()) {
    val isLoading by vm.isLoading.collectAsStateWithLifecycle()
    LoginScreen(isLoading, onLogin = vm::onLogin)
}