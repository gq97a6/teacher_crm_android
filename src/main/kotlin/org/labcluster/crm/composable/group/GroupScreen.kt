package org.labcluster.crm.composable.group

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.labcluster.crm.composable.shared.PreviewSample
import org.labcluster.crm.shared.Mock
import org.labcluster.crm.shared.model.Group

@Preview
@Composable
private fun Preview() = PreviewSample { GroupScreen() }

@Composable
fun GroupScreen(group: Group = Mock.groups.random()) {

}