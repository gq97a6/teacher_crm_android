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
import org.labcluster.crm.android.SplashViewKey
import org.labcluster.crm.android.chronos.Chronos
import org.labcluster.crm.android.screen.calendar.CalendarViewModelState
import org.labcluster.crm.android.screen.group.GroupViewModelState
import org.labcluster.crm.android.screen.grouplist.GroupListViewModelState
import org.labcluster.crm.android.screen.lesson.LessonViewModelState
import org.labcluster.crm.android.screen.login.LoginViewModelState
import org.labcluster.crm.android.screen.topic.TopicViewModelState
import org.labcluster.crm.shared.Open

@Open
@Serializable
class AppState {
    @Transient
    var dumpPath: String? = null

    @Transient
    var chronos = Chronos()

    @Transient
    var backstack = MutableStateFlow(NavBackStack<NavKey>(SplashViewKey()))

    @Transient
    var isNavigationEnabled = MutableStateFlow(false)

    //Screen related global state =================================================================

    @Transient //Always fetch new on boot
    var calendar = CalendarViewModelState()

    @Transient //Always fetch new on boot
    var groupList = GroupListViewModelState()

    var lesson = LessonViewModelState()
    var topic = TopicViewModelState()
    var group = GroupViewModelState()
    var login = LoginViewModelState()

    //====================================================================================

    @Transient
    private val aLock = Mutex(false)

    suspend fun lockState() = aLock.lock()
    suspend fun unlockState() = aLock.unlock()

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