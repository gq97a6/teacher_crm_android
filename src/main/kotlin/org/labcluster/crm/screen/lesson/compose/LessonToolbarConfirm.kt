package org.labcluster.crm.screen.lesson.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.labcluster.crm.composable.PreviewScaffold

@Preview
@Composable
private fun Preview() = PreviewScaffold {
    Box(
        Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 30.dp)
    ) {
        LessonToolbarConfirm()
    }
}

@Composable
fun LessonToolbarConfirm(
    onConfirmClicked: () -> Unit = {},
    onCancelClicked: () -> Unit = {}
) {
    HorizontalFloatingToolbar(
        expanded = false,
        collapsedShadowElevation = 5.dp
    ) {
        TextButton(onConfirmClicked) {
            Icon(
                imageVector = Icons.Outlined.Check,
                contentDescription = ""
            )
            Spacer(Modifier.width(10.dp))
            Text(
                "Akceptuj",
                modifier = Modifier
            )
        }
        Spacer(Modifier.width(5.dp))
        TextButton(onCancelClicked) {
            Icon(
                Icons.Outlined.Close, ""
            )
            Spacer(Modifier.width(10.dp))
            Text(
                "Anuluj",
                modifier = Modifier
            )
        }
    }
}