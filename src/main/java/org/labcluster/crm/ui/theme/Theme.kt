package org.labcluster.crm.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MotionScheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable

@Composable
fun Theme(
    darkTheme: Boolean,
    content: @Composable() () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) darkColorScheme else lightColorScheme,
        typography = Typography(),
        content = content,
        motionScheme = MotionScheme.expressive(),
    )
}

@Composable
fun Theme(
    colorScheme: ColorScheme,
    content: @Composable() () -> Unit
) {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        content = content
    )
}
