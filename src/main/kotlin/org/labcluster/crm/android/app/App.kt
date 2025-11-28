package org.labcluster.crm.android.app

import android.app.Application


class App : Application() {

    internal companion object {
        var app: App = App()
        var state: AppState = AppState()
        var api: AppApi = AppApi(AppState(), "")
    }

    override fun onCreate() {
        super.onCreate()
        app = this
    }
}