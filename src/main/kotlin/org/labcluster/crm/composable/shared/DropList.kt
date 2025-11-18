package org.labcluster.crm.composable.shared

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import org.labcluster.crm.cs

@Preview
@Composable
private fun Preview() = PreviewScaffold(showAppBar = false) {
    DropList(
        value = "Option 10",
        items = List(30) { "Option ${it + 1}" },
        modifier = Modifier.padding(15.dp)
    )
}

@Composable
fun DropList(
    value: String,
    modifier: Modifier = Modifier,
    items: List<String> = listOf(),
    onValueSet: (String) -> Unit = {},
    label: String = "",
    readOnly: Boolean = false,
    height: Dp = 300.dp,
) {
    var isExpanded by remember { mutableStateOf(false) }
    val iconAngle by animateFloatAsState(if (isExpanded) 180f else 0f, label = "")

    Column(modifier) {
        Box(modifier = Modifier.height(IntrinsicSize.Min)) {
            OutlinedTextField(
                value = value,
                label = { Text(text = label) },
                singleLine = true,
                readOnly = true,
                trailingIcon = {
                    if (!readOnly) Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "",
                        modifier = Modifier.rotate(iconAngle)
                    )
                },
                onValueChange = { },
                modifier = Modifier.fillMaxWidth()
            )

            //Prevent clicks for text field
            Surface(
                color = Color.Transparent,
                content = {},
                modifier = Modifier.fillMaxSize()
            )

            if (!isExpanded) Surface(
                color = Color.Transparent,
                content = {},
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp)
                    .clip(MaterialTheme.shapes.extraSmall)
                    .clickable(enabled = !readOnly) { isExpanded = true }
            )
        }

        BoxWithConstraints {
            Popup(onDismissRequest = { isExpanded = false }) {
                Column(Modifier.width(with(LocalDensity.current) { maxWidth })) {
                    AnimatedVisibility(
                        visible = isExpanded,
                        enter = fadeIn(
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = LinearOutSlowInEasing
                            )
                        ) + expandVertically(
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = LinearOutSlowInEasing
                            )
                        ),
                        exit = fadeOut(
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = LinearOutSlowInEasing
                            )
                        ) + shrinkVertically(
                            animationSpec = tween(
                                durationMillis = 200,
                                easing = LinearOutSlowInEasing
                            )
                        )
                    ) {
                        LazyColumn(
                            Modifier
                                .heightIn(0.dp, height)
                                .padding(top = 10.dp)
                                .background(cs.surfaceContainer, RoundedCornerShape(5.dp))
                        ) {
                            items(items.size) { index ->
                                if (index != 0) HorizontalDivider()
                                DropdownMenuItem(
                                    text = { Text(items[index]) },
                                    onClick = {
                                        isExpanded = false
                                        onValueSet(items[index])
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}