package org.labcluster.crm.android.screen.topic

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.labcluster.crm.android.composable.PreviewScaffold
import org.labcluster.crm.android.cs
import org.labcluster.crm.android.screen.topic.compose.TopicAppBar
import kotlin.random.Random

@Preview
@Composable
private fun Preview() = PreviewScaffold(false) { TopicView() }

@Composable
fun TopicView(vm: TopicViewModel = viewModel()) {

    val topic by vm.state.topic.topic.collectAsStateWithLifecycle()

    val mockText = remember {
        buildString {
            repeat(100) {
                append("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
                if (it < 99) append(" ")
            }
        }
    }

    Scaffold(
        modifier = Modifier,
        topBar = { TopicAppBar(title = topic.name) },
        contentWindowInsets = WindowInsets(left = 15.dp, right = 15.dp)
    ) { paddingValues ->
        LazyColumn(Modifier.padding(paddingValues)) {
            items(20) {
                Text(
                    text = mockText.take(11),
                    color = cs.onBackground,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = mockText.take(Random.nextInt(50, 400)),
                    color = cs.onBackground
                )

                Spacer(Modifier.height(10.dp))
            }
        }
    }
}