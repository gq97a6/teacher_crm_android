package org.labcluster.crm.screen.grouplist.compose

import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import org.labcluster.crm.composable.PreviewScaffold
import org.labcluster.crm.cs

@Preview
@Composable
private fun Preview() = PreviewScaffold(showAppBar = false) { GroupListAppBar() }

@Composable
fun GroupListAppBar() {
    TopAppBar(
        title = {
            Text(
                "Twoje grupy",
                fontWeight = FontWeight.Black,
                color = cs.tertiary
            )
        }
    )
}