package org.labcluster.crm.screen.topic

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.labcluster.crm.Mock.lorem
import org.labcluster.crm.composable.PreviewScaffold
import org.labcluster.crm.cs
import org.labcluster.crm.screen.topic.compose.TopicAppBar
import kotlin.random.Random

@Preview
@Composable
private fun Preview() = PreviewScaffold(false) { TopicView() }

@Composable
fun TopicView(vm: TopicViewModel = viewModel()) {

    val topic by vm.state.topic.topic.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier,
        topBar = { TopicAppBar(title = topic.name) },
        contentWindowInsets = WindowInsets(left = 15.dp, right = 15.dp)
    ) { paddingValues ->
        LazyColumn(Modifier.padding(paddingValues)) {
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
}