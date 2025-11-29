package org.labcluster.crm.android

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.SaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.labcluster.crm.android.app.App.Companion.state
import org.labcluster.crm.android.composable.MyNavigationDrawer
import org.labcluster.crm.android.composable.PreviewScaffold
import org.labcluster.crm.android.screen.calendar.CalendarView
import org.labcluster.crm.android.screen.group.GroupView
import org.labcluster.crm.android.screen.grouplist.GroupListView
import org.labcluster.crm.android.screen.lesson.LessonView
import org.labcluster.crm.android.screen.login.LoginView
import org.labcluster.crm.android.screen.setting.SettingView
import org.labcluster.crm.android.screen.splash.SplashView
import org.labcluster.crm.android.screen.topic.TopicView
import org.labcluster.crm.android.storage.Storage.dumpToFile
import org.labcluster.crm.android.theme.Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        actionBar?.hide()

        setContent {
            Theme(isSystemInDarkTheme()) {
                Box(Modifier.background(cs.background)) {
                    ScreenContent()
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        //Get optional extra sent from widget
        val screen = intent.getStringExtra("screen")
        if (screen != null) lifecycleScope.launch {
            state.alter {
                backstack.value.clear()
                when (screen) {
                    "calendar" -> backstack.value.addFirst(CalendarViewKey())
                    "groups" -> backstack.value.addFirst(GroupListViewKey())
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        IntentFilter().apply {
            addAction(Intent.ACTION_TIMEZONE_CHANGED)
            addAction(Intent.ACTION_TIME_CHANGED)
            addAction(Intent.ACTION_DATE_CHANGED)
        }.let {
            ContextCompat.registerReceiver(
                this, timeChangedReceiver,
                it,
                ContextCompat.RECEIVER_EXPORTED
            )
        }
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(timeChangedReceiver)

        Log.i("DUPA", "DUMP!")
        state.dumpPath?.ifBlank { null }?.let {
            state.dumpToFile(it)
        }
    }

    private val timeChangedReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            state.alter {
                chronos.notifyTimeChanged()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() = PreviewScaffold { ScreenContent() }

@Serializable
class LessonViewKey() : NavKey

@Serializable
class TopicViewKey() : NavKey

@Serializable
class GroupListViewKey() : NavKey

@Serializable
class GroupViewKey() : NavKey

@Serializable
class CalendarViewKey() : NavKey

@Serializable
class SettingViewKey() : NavKey

@Serializable
class LoginViewKey() : NavKey

@Serializable
class SplashViewKey() : NavKey

@Composable
fun BoxScope.ScreenContent() {
    val backstack by state.backstack.collectAsStateWithLifecycle()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    MyNavigationDrawer(
        backstack,
        drawerState
    ) {
        NavDisplay(
            backStack = backstack,
            entryDecorators = listOf(
                SaveableStateHolderNavEntryDecorator(rememberSaveableStateHolder()),
                rememberViewModelStoreNavEntryDecorator(),
                NavEntryDecorator { Box { it.Content() } } //Required even if context is BoxScope
            ),
            transitionSpec = {
                ContentTransform(
                    fadeIn(animationSpec = tween(200)),
                    fadeOut(animationSpec = tween(200)),
                )
            },
            popTransitionSpec = {
                ContentTransform(
                    fadeIn(animationSpec = tween(200)),
                    fadeOut(animationSpec = tween(200)),
                )
            },
            entryProvider = { entryProvider(it) }
        )
    }
}

fun <T> BoxScope.entryProvider(key: T): NavEntry<NavKey> = when (key) {
    is LessonViewKey -> NavEntry(key = key) { LessonView() }
    is TopicViewKey -> NavEntry(key = key) { TopicView() }
    is GroupViewKey -> NavEntry(key = key) { GroupView() }
    is GroupListViewKey -> NavEntry(key = key) { GroupListView() }
    is CalendarViewKey -> NavEntry(key = key) { CalendarView() }
    is SettingViewKey -> NavEntry(key = key) { SettingView() }
    is LoginViewKey -> NavEntry(key = key) { LoginView() }
    is SplashViewKey -> NavEntry(key = key) { SplashView() }
    else -> throw Error()
}