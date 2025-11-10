package org.labcluster.crm.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearWavyProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.labcluster.crm.utils.cs

@Composable
fun WavyDivider(
    modifier: Modifier = Modifier,
    color: Color = cs.primary,
    thickness: Dp = 5.dp,
    wavelength: Dp = 30.dp
) {
    LinearWavyProgressIndicator(
        progress = { 1f },
        modifier = modifier
            .height(10.dp)
            .fillMaxWidth(),
        stroke = Stroke(
            width = with(LocalDensity.current) { thickness.toPx() },
            cap = StrokeCap.Round,
        ),
        color = color,
        amplitude = { 1f },
        wavelength = wavelength,
        waveSpeed = 0.dp
    )
}