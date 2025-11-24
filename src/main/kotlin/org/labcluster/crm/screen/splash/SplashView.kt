package org.labcluster.crm.screen.splash

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.labcluster.crm.composable.PreviewScaffold
import org.labcluster.crm.cs

@Preview
@Composable
private fun Preview() = PreviewScaffold(false) { SplashView() }

@Composable
fun SplashView(vm: SplashViewModel = viewModel()) {

    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = .95f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(
        Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .padding(bottom = 100.dp)
            .scale(scale),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Lorem",
            fontSize = 85.sp,
            fontWeight = FontWeight.Black,
            color = cs.primary,
            style = TextStyle(
                shadow = Shadow(
                    color = Color.Black, offset = Offset(6f, 6f), blurRadius = 3f
                )
            )
        )
        Text(
            "Ipsum",
            fontSize = 85.sp,
            fontWeight = FontWeight.Bold,
            color = cs.tertiary,
            modifier = Modifier.offset(y = (-20).dp),
            style = TextStyle(
                shadow = Shadow(
                    color = Color.Black, offset = Offset(5f, 5f), blurRadius = 3f
                )
            )
        )
    }
}