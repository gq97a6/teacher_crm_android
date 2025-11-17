package org.labcluster.crm.composable.calendar

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Square
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.labcluster.crm.PreviewSample

@Preview
@Composable
private fun Preview() = PreviewSample { CalendarToolbar() }

@Composable
fun BoxScope.CalendarToolbar(
    onPreviousClicked: () -> Unit = {},
    onCurrentSelected: () -> Unit = {},
    onNextClicked: () -> Unit = {},
) {
    Row(
        Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 20.dp)
    ) {
        ElevatedButton(
            onClick = onPreviousClicked,
            shape = ShapeDefaults.Medium,
            modifier = Modifier.size(70.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                contentDescription = ""
            )
        }

        Spacer(Modifier.width(20.dp))

        ElevatedButton(
            onClick = onCurrentSelected,
            shape = ShapeDefaults.Medium,
            modifier = Modifier.size(70.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Square,
                contentDescription = ""
            )
        }

        Spacer(Modifier.width(20.dp))

        ElevatedButton(
            onClick = onNextClicked,
            shape = ShapeDefaults.Medium,
            modifier = Modifier.size(70.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                contentDescription = ""
            )
        }
    }
}

