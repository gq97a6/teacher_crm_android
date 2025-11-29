package org.labcluster.crm.android.app

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.labcluster.crm.android.mock.Mock
import org.labcluster.crm.shared.Open

@Open
class App : Application() {

    internal companion object {
        var app: App = Mock.MockApp()
        var state: AppState = AppState()
        var api: AppApi = AppApi("")
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        boot()
    }

    fun boot() {
        CoroutineScope(Dispatchers.IO).launch {
            AppStartup.boot()
        }
    }
}

