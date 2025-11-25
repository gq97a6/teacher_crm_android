package org.labcluster.crm.android

import org.junit.Test
import org.labcluster.crm.android.Storage.prepareSave

class StateDumpTest {
    @Test
    fun testSerialization() {
        val state = Mock.state
        assert(state.prepareSave().isNotEmpty())
    }
}