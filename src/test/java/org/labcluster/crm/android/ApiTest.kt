package org.labcluster.crm.android

import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.labcluster.crm.android.app.AppApi
import org.labcluster.crm.android.app.AppState

class ApiTest {

    @Test
    fun testApi() = runBlocking {
        AppState()
        val api = AppApi("https://crm.labcluster.org/api")
        val tt = api.getTeacherTimetable("01ef4c39-9577-4eeb-a017-b3e1a9e38864")
        print(tt)
        assert(tt.isNotEmpty())
    }
}