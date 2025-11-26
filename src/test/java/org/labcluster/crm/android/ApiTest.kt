package org.labcluster.crm.android

import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.labcluster.crm.android.app.AppApi
import org.labcluster.crm.android.app.AppState

class ApiTest {

    @Test
    fun testApi() = runBlocking {
        val state = AppState()
        val api = AppApi(state, "http://localhost")
        assert(api.healthCheck())
    }
}