package org.labcluster.crm.android

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.labcluster.crm.android.app.AppApi
import org.labcluster.crm.android.app.AppState

@RunWith(AndroidJUnit4::class)
class ApiTest {

    @Test
    fun testApi() = runBlocking {
        val state = AppState()
        val api = AppApi(state, "https://crm.labcluster.org/api")
        val tt = api.getTeacherTimetable("01ef4c39-9577-4eeb-a017-b3e1a9e38864")
        print(tt)
        assert(tt.isNotEmpty())
    }
}