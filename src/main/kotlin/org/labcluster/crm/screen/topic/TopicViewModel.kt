@file:UseContextualSerialization(MutableStateFlow::class)

package org.labcluster.crm.screen.topic

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseContextualSerialization
import org.labcluster.crm.Open
import org.labcluster.crm.app.App
import org.labcluster.crm.app.AppState
import org.labcluster.crm.shared.model.Topic

@Open
class TopicViewModel(val state: AppState = App.state) : ViewModel() {

    @Open
    @Serializable
    class State() {
        val isTopicSet = MutableStateFlow(false)
        val topic = MutableStateFlow(Topic())
        fun setTopic(topic: Topic) {
            this.topic.value = topic
            isTopicSet.value = true
        }
    }
}