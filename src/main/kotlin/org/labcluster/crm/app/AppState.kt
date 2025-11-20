package org.labcluster.crm.app

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.labcluster.crm.CalendarViewKey
import org.labcluster.crm.Open
import org.labcluster.crm.chronos.Chronos
import org.labcluster.crm.screen.calendar.CalendarViewModel
import org.labcluster.crm.screen.group.GroupViewModel
import org.labcluster.crm.screen.grouplist.GroupListViewModel
import org.labcluster.crm.screen.lesson.LessonViewModel
import org.labcluster.crm.screen.topic.TopicViewModel

@Open
class AppState {
    val chronos = Chronos()
    val backstack = MutableStateFlow(NavBackStack<NavKey>(CalendarViewKey()))

    val calendar = CalendarViewModel.State()
    val group = GroupViewModel.State()
    val groupList = GroupListViewModel.State()
    val lesson = LessonViewModel.State()
    val topic = TopicViewModel.State()

    private val aLock = Mutex(false)
    fun alter(action: suspend AppState.() -> Unit) {
        runBlocking {
            aLock.withLock {
                this@AppState.action()
            }
        }
    }
}