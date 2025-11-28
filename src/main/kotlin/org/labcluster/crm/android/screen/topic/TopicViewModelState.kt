@file:UseContextualSerialization(MutableStateFlow::class)

package org.labcluster.crm.android.screen.topic

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.UseContextualSerialization
import org.labcluster.crm.android.Open
import org.labcluster.crm.shared.model.Topic

@Open
@Serializable
class TopicViewModelState() {

    @Transient
    val isTopicSet = MutableStateFlow(false)
    val topic = MutableStateFlow(Topic())

    fun setTopic(topic: Topic) {
        this.topic.value = topic
        isTopicSet.value = true
    }
}