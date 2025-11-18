package org.labcluster.crm.composable.lesson

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Book
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
import org.labcluster.crm.composable.shared.PreviewScaffold

@Preview
@Composable
private fun Preview() = PreviewScaffold {
    Box(
        Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 30.dp)
    ) {
        LessonToolbarStart()
    }
}

@Composable
fun LessonToolbarStart(
    onStartClicked: () -> Unit = {},
    onTopicClicked: () -> Unit = {},
    onCourseClicked: () -> Unit = {}
) {
    HorizontalFloatingToolbar(expanded = false) {
        TextButton(onStartClicked) {
            Icon(
                imageVector = Icons.Outlined.PlayCircle,
                contentDescription = ""
            )
            Spacer(Modifier.width(10.dp))
            Text(
                "Start",
                modifier = Modifier
            )
        }
        Spacer(Modifier.width(5.dp))
        TextButton(onTopicClicked) {
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
        TextButton(onCourseClicked) {
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