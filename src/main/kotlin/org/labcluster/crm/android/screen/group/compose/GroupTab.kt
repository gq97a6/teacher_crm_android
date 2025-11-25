package org.labcluster.crm.android.screen.group.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.labcluster.crm.android.composable.PreviewScaffold

@Preview
@Composable
private fun Preview() = PreviewScaffold {
    Box(Modifier.padding(it)) {
        GroupTab()
    }
}

@Composable
fun GroupTab(
    selectedTabIndex: Int = 0,
    onLessonClicked: () -> Unit = {},
    onStudentsClicked: () -> Unit = {}
) {
    SecondaryTabRow(selectedTabIndex = selectedTabIndex) {
        Tab(
            selected = false,
            onClick = onLessonClicked,
            text = { Text(text = "Lekcje") }
        )
        Tab(
            selected = false,
            onClick = onStudentsClicked,
            text = { Text(text = "Uczniowie") }
        )
    }
}