package org.labcluster.crm.composable.lesson

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.material.icons.outlined.Topic
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.labcluster.crm.PreviewSample

@Preview
@Composable
private fun Preview() = PreviewSample { LessonToolbar() }

@Composable
fun BoxScope.LessonToolbar(hasBegun: Boolean = false) {

    HorizontalFloatingToolbar(
        expanded = false,
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 30.dp)
    ) {
        TextButton({}) {
            Icon(
                imageVector = if (hasBegun) Icons.Outlined.Edit
                else Icons.Outlined.PlayCircle,
                contentDescription = ""
            )
            Spacer(Modifier.width(10.dp))
            Text(
                if (hasBegun) "Edytuj" else "Start",
                modifier = Modifier
            )
        }
        Spacer(Modifier.width(5.dp))
        TextButton({}) {
            Icon(
                Icons.Outlined.Topic, ""
            )
            Spacer(Modifier.width(10.dp))
            Text(
                "Temat",
                modifier = Modifier
            )
        }
        Spacer(Modifier.width(5.dp))
        TextButton({}) {
            Icon(
                Icons.Outlined.Book, ""
            )
            Spacer(Modifier.width(10.dp))
            Text(
                "Kurs",
                modifier = Modifier
            )
        }
    }
}