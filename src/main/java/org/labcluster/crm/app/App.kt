package org.labcluster.crm.app

import android.app.Application


class App : Application() {
    companion object {
        var app = App()
        var state = AppState()
    }

    override fun onCreate() {
        super.onCreate()
        app = this
    }
}