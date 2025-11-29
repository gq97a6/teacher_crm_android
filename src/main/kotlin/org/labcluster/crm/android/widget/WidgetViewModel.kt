package org.labcluster.crm.android.widget

import androidx.lifecycle.ViewModel
import org.labcluster.crm.android.app.App
import org.labcluster.crm.android.app.AppState
import org.labcluster.crm.shared.Open

@Open
class WidgetViewModel(val state: AppState = App.state) : ViewModel()