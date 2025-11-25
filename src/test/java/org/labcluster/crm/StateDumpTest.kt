package org.labcluster.crm

import org.junit.Test
import org.labcluster.crm.Storage.prepareSave

class StateDumpTest {
    @Test
    fun testSerialization() {
        val state = Mock.state
        assert(state.prepareSave().isNotEmpty())
    }
}