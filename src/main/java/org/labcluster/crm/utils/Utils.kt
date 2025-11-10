package org.labcluster.crm.utils

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import org.labcluster.crm.ui.theme.Theme

fun composeConstruct(
    context: Context,
    isDark: Boolean = true,
    content: @Composable () -> Unit
): ComposeView {
    return ComposeView(context).apply { setContent { Theme(isDark, content) } }
}

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

fun String.containsAll(filter: String): Boolean {
    filter.forEach { if (!contains(it)) return false }
    return true
}

infix fun <E> (() -> E?).catch(e: E): E = try {
    this() ?: e
} catch (_: Exception) {
    e
}