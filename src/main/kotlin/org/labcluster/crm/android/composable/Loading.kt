package org.labcluster.crm.android.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
private fun Preview() = PreviewScaffold { Loading(it) }

@Composable
fun Loading(paddingValues: PaddingValues = PaddingValues()) {
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .padding(bottom = 80.dp)
            .fillMaxSize(),
    ) {
        CircularWavyProgressIndicator(Modifier.align(Alignment.Center))
    }
}