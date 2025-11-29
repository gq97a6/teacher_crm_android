package org.labcluster.crm.android.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.labcluster.crm.android.app.App.Companion.state
import org.labcluster.crm.android.cs
import org.labcluster.crm.android.mock.Mock
import org.labcluster.crm.android.theme.Theme
import org.labcluster.crm.android.theme.darkColorScheme

@Composable
fun PreviewSimple(
    scheme: ColorScheme = darkColorScheme,
    doMock: Boolean = false,
    content: @Composable BoxScope.() -> Unit
) {
    //Mock app state
    if (doMock) state = Mock.state

    Theme(scheme) {
        Box(Modifier.background(cs.background)) {
            content()
        }
    }
}