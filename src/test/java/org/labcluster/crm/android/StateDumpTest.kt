package org.labcluster.crm.android

import org.junit.Test
import org.labcluster.crm.android.mock.Mock
import org.labcluster.crm.android.storage.Storage.prepareSave

class StateDumpTest {
    @Test
    fun testSerialization() {
        val state = Mock.state
        assert(state.prepareSave().isNotEmpty())
    }
}