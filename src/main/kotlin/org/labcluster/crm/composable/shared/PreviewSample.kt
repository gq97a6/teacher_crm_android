package org.labcluster.crm.composable.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.labcluster.crm.app.App.Companion.state
import org.labcluster.crm.cs
import org.labcluster.crm.objects.Mock
import org.labcluster.crm.theme.Theme
import org.labcluster.crm.theme.darkColorScheme

@Composable
fun PreviewSample(
    showAppBar: Boolean = true,
    scheme: ColorScheme = darkColorScheme,
    content: @Composable BoxScope.(PaddingValues) -> Unit
) {
    //Mock app state
    state = Mock.state

    Theme(scheme) {
        Scaffold(
            modifier = Modifier,
            topBar = {
                if (showAppBar) TopAppBar(
                    title = {
                        Text(
                            "Title",
                            fontWeight = FontWeight.Black,
                            color = cs.tertiary
                        )
                    },
                    subtitle = {
                        Text(
                            "Subtitle",
                            fontWeight = FontWeight.Black,
                            color = cs.primary
                        )
                    },
                    actions = {
                        Box(modifier = Modifier) {
                            IconButton(onClick = {}) {
                                Icon(
                                    imageVector = Icons.Default.MoreVert,
                                    contentDescription = "More options",
                                    tint = cs.primary
                                )
                            }
                        }
                    }
                )
            },
            contentWindowInsets = WindowInsets(left = 15.dp, right = 15.dp)
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .imePadding()
                    .fillMaxSize()
                    .background(cs.background)
            ) {
                content(paddingValues)
            }
        }
    }
}