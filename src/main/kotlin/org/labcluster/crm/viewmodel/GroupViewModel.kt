package org.labcluster.crm.viewmodel

import androidx.lifecycle.ViewModel
import org.labcluster.crm.app.App
import org.labcluster.crm.app.AppState

class GroupViewModel(val state: AppState = App.state) : ViewModel()