package org.labcluster.crm.android.screen.topic

import androidx.lifecycle.ViewModel
import org.labcluster.crm.android.Open
import org.labcluster.crm.android.app.App
import org.labcluster.crm.android.app.AppState

@Open
class TopicViewModel(val state: AppState = App.state) : ViewModel()