package org.labcluster.crm.android.screen.topic.compose

import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.labcluster.crm.android.composable.PreviewScaffold
import org.labcluster.crm.android.cs

@Preview
@Composable
private fun Preview() = PreviewScaffold(showAppBar = false) { TopicAppBar() }

@Composable
fun TopicAppBar(
    title: String = "Introduction to Programming with Python and Kotlin",
    subtitle: String? = "10 tematów"
) {
    TopAppBar(
        title = {
            Text(
                title,
                fontWeight = FontWeight.Black,
                color = cs.tertiary,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth(.97f)
                    .basicMarquee(
                        spacing = MarqueeSpacing(10.dp),
                        velocity = 60.dp
                    )
            )
        },
        subtitle = {
            if (subtitle != null) Text(
                subtitle,
                fontWeight = FontWeight.Black,
                color = cs.primary
            )
        }
    )
}