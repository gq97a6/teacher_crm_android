package org.labcluster.crm.screen.topic

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.labcluster.crm.Open
import org.labcluster.crm.app.App
import org.labcluster.crm.app.AppState
import org.labcluster.crm.shared.model.Topic

@Open
class TopicViewModel(val state: AppState = App.state) : ViewModel() {

    @Open
    class State() {
        val topic = MutableStateFlow(Topic())
    }
}