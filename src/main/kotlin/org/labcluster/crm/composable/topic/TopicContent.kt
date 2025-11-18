package org.labcluster.crm.composable.topic

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.labcluster.crm.composable.shared.PreviewSample
import org.labcluster.crm.cs
import org.labcluster.crm.objects.Mock.lorem
import kotlin.random.Random


@Preview
@Composable
private fun Preview() = PreviewSample { TopicContent() }

@Composable
fun TopicContent() {
    LazyColumn {
        items(20) {
            Text(
                text = lorem.take(11),
                color = cs.onBackground,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = lorem.take(Random.nextInt(50, 400)),
                color = cs.onBackground
            )

            Spacer(Modifier.height(10.dp))
        }
    }
}