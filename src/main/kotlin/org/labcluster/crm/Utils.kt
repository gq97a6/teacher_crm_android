package org.labcluster.crm

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.labcluster.crm.theme.Theme
import org.labcluster.crm.theme.darkColorScheme

fun ComponentActivity.composeConstruct(
    isDark: Boolean = true,
    content: @Composable () -> Unit
) {
    setContent {
        Theme(isDark) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(cs.background)
            ) {
                content()
            }
        }
    }
}

val cs: ColorScheme
    @Composable
    get() = MaterialTheme.colorScheme

@Composable
fun PreviewSample(scheme: ColorScheme = darkColorScheme, content: @Composable BoxScope.() -> Unit) {
    Theme(scheme) {
        Box(
            Modifier
                .fillMaxSize()
                .background(cs.background)
        ) {
            content()
        }
    }
}