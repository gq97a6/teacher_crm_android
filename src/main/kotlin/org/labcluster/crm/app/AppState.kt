package org.labcluster.crm.app

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.labcluster.crm.LoginViewKey
import org.labcluster.crm.Open
import org.labcluster.crm.chronos.Chronos
import org.labcluster.crm.screen.calendar.CalendarViewModel
import org.labcluster.crm.screen.group.GroupViewModel
import org.labcluster.crm.screen.grouplist.GroupListViewModel
import org.labcluster.crm.screen.lesson.LessonViewModel
import org.labcluster.crm.screen.topic.TopicViewModel

@Open
@Serializable
class AppState {
    @Transient
    var dumpPath: String? = null

    @Transient
    val chronos = Chronos()

    @Transient
    val backstack = MutableStateFlow(NavBackStack<NavKey>(LoginViewKey()))

    val calendar = CalendarViewModel.State()
    val group = GroupViewModel.State()
    val groupList = GroupListViewModel.State()
    val lesson = LessonViewModel.State()
    val topic = TopicViewModel.State()

    @Transient
    private val aLock = Mutex(false)
    fun alter(action: suspend AppState.() -> Unit) {
        runBlocking {
            aLock.withLock {
                this@AppState.action()
            }
        }
    }
}