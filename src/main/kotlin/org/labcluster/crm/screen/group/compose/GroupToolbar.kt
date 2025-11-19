package org.labcluster.crm.screen.group.compose

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ToggleButton
import androidx.compose.material3.ToggleButtonColors
import androidx.compose.material3.ToggleButtonShapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.labcluster.crm.composable.shared.PreviewScaffold
import org.labcluster.crm.cs

@Preview
@Composable
private fun Preview() = PreviewScaffold { GroupToolbar() }

@Composable
fun BoxScope.GroupToolbar(
    lessonOnClick: () -> Unit = {},
    studentsOnClick: () -> Unit = {},
) {
    var isLessonSelected by remember { mutableStateOf(false) }

    val fullShape = remember {
        ToggleButtonShapes(
            shape = RoundedCornerShape(100),
            pressedShape = RoundedCornerShape(100),
            checkedShape = RoundedCornerShape(100)
        )
    }

    //androidx.compose.ui.graphics.Shadow
    //androidx.compose.ui.graphics.shadow.Shadow

    HorizontalFloatingToolbar(
        expanded = false,
        collapsedShadowElevation = 5.dp,
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 30.dp)
    ) {
        ToggleButton(
            isLessonSelected,
            onCheckedChange = { isLessonSelected = true },
            shapes = fullShape,
            colors = ToggleButtonColors(
                containerColor = cs.surfaceContainer,
                contentColor = cs.secondary,
                disabledContainerColor = cs.secondaryContainer,
                disabledContentColor = cs.secondary,
                checkedContainerColor = cs.secondaryContainer,
                checkedContentColor = cs.secondary
            )
        ) {
            Icon(
                imageVector = Icons.Outlined.Book,
                contentDescription = ""
            )
            Spacer(Modifier.width(5.dp))
            Text(
                "Lekcje",
                modifier = Modifier
            )
        }
        Spacer(Modifier.width(10.dp))
        ToggleButton(
            !isLessonSelected,
            onCheckedChange = { isLessonSelected = false },
            shapes = fullShape,
            colors = ToggleButtonColors(
                containerColor = cs.surfaceContainer,
                contentColor = cs.secondary,
                disabledContainerColor = cs.surfaceContainer,
                disabledContentColor = cs.secondary,
                checkedContainerColor = cs.secondaryContainer,
                checkedContentColor = cs.secondary
            )
        ) {
            Icon(
                Icons.Outlined.Group, ""
            )
            Spacer(Modifier.width(10.dp))
            Text(
                "Uczniowie",
                modifier = Modifier
            )
        }
    }
}