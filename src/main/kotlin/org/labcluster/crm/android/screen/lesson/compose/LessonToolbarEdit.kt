package org.labcluster.crm.android.screen.lesson.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Edit
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
import org.labcluster.crm.android.composable.PreviewScaffold

@Preview
@Composable
private fun Preview() = PreviewScaffold {
    Box(
        Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 30.dp)
    ) {
        LessonToolbarEdit()
    }
}

@Composable
fun LessonToolbarEdit(
    onEditClicked: () -> Unit = {},
    onTopicClicked: () -> Unit = {},
    onCourseClicked: () -> Unit = {}
) {
    HorizontalFloatingToolbar(
        expanded = false,
        collapsedShadowElevation = 5.dp
    ) {
        TextButton(onEditClicked) {
            Icon(
                imageVector = Icons.Outlined.Edit,
                contentDescription = ""
            )
            Spacer(Modifier.width(10.dp))
            Text(
                "Edytuj",
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