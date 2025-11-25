package org.labcluster.crm.android.app

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.labcluster.crm.android.Open
import org.labcluster.crm.android.SplashViewKey
import org.labcluster.crm.android.chronos.Chronos
import org.labcluster.crm.android.screen.calendar.CalendarViewModel
import org.labcluster.crm.android.screen.group.GroupViewModel
import org.labcluster.crm.android.screen.grouplist.GroupListViewModel
import org.labcluster.crm.android.screen.lesson.LessonViewModel
import org.labcluster.crm.android.screen.login.LoginViewModel
import org.labcluster.crm.android.screen.topic.TopicViewModel

@Open
@Serializable
class AppState {
    @Transient
    var dumpPath: String? = null

    @Transient
    val chronos = Chronos()

    @Transient
    val backstack = MutableStateFlow(NavBackStack<NavKey>(SplashViewKey()))

    @Transient
    val isNavigationEnabled = MutableStateFlow(false)

    //Screen related global state
    val calendar = CalendarViewModel.State()
    val group = GroupViewModel.State()
    val groupList = GroupListViewModel.State()
    val lesson = LessonViewModel.State()
    val topic = TopicViewModel.State()
    val login = LoginViewModel.State()

    //Api persistent state
    val apiState = AppApi.State()

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