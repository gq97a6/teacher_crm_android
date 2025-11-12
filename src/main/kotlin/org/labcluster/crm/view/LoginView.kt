package org.labcluster.crm.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import org.labcluster.crm.PreviewSample
import org.labcluster.crm.composable.LoginScreen
import org.labcluster.crm.viewmodel.LoginViewModel

@Preview
@Composable
private fun Preview() = PreviewSample { LoginView() }

@Composable
fun LoginView(vm: LoginViewModel = viewModel()) {
    LoginScreen({}, {})
}