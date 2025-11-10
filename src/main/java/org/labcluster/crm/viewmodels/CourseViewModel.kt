package org.labcluster.crm.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import org.labcluster.crm.app.App
import org.labcluster.crm.app.AppState

class CourseViewModel(
    app: Application = App.app,
    state: AppState = App.state
) : ViewModel() {

}