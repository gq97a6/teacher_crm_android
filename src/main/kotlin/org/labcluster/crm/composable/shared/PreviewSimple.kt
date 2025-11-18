package org.labcluster.crm.composable.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import org.labcluster.crm.app.App.Companion.state
import org.labcluster.crm.objects.Mock
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
        Box {
            content()
        }
    }
}