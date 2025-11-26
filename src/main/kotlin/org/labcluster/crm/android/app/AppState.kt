package org.labcluster.crm.android.app

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withTimeoutOrNull
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import org.labcluster.crm.android.Open
import org.labcluster.crm.android.SplashViewKey
import org.labcluster.crm.android.chronos.Chronos
import org.labcluster.crm.android.screen.calendar.CalendarViewModelState
import org.labcluster.crm.android.screen.group.GroupViewModelState
import org.labcluster.crm.android.screen.grouplist.GroupListViewModelState
import org.labcluster.crm.android.screen.lesson.LessonViewModelState
import org.labcluster.crm.android.screen.login.LoginViewModelState
import org.labcluster.crm.android.screen.topic.TopicViewModelState

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
    val calendar = CalendarViewModelState()
    val group = GroupViewModelState()
    val groupList = GroupListViewModelState()
    val lesson = LessonViewModelState()
    val topic = TopicViewModelState()
    val login = LoginViewModelState()

    //Api persistent state
    val apiState = AppApi.State()

    @Transient
    private val aLock = Mutex(false)
    fun alter(action: AppState.() -> Unit) {
        runBlocking {
            withTimeoutOrNull(50000) {
                aLock.withLock {
                    this@AppState.action()
                }
            }
        }
    }

    fun alter(scope: CoroutineScope, action: suspend AppState.() -> Unit): Job {
        return scope.launch {
            withTimeoutOrNull(50000) {
                aLock.withLock {
                    this@AppState.action()
                }
            }
        }
    }
}