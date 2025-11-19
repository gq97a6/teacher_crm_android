package org.labcluster.crm.composable.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.labcluster.crm.Mock
import org.labcluster.crm.app.App.Companion.state
import org.labcluster.crm.cs
import org.labcluster.crm.theme.Theme
import org.labcluster.crm.theme.darkColorScheme

@Composable
fun PreviewSimple(
    scheme: ColorScheme = darkColorScheme,
    content: @Composable BoxScope.() -> Unit
) {
    //Mock app state
    state = Mock.state

    Theme(scheme) {
        Box(Modifier.background(cs.background)) {
            content()
        }
    }
}