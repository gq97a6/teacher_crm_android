package org.labcluster.crm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
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
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.SaveableStateHolderNavEntryDecorator
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import kotlinx.serialization.Serializable
import org.labcluster.crm.app.App.Companion.state
import org.labcluster.crm.composable.shared.MyNavigationDrawer
import org.labcluster.crm.composable.shared.PreviewScaffold
import org.labcluster.crm.screen.calendar.CalendarView
import org.labcluster.crm.screen.group.GroupView
import org.labcluster.crm.screen.lesson.LessonView
import org.labcluster.crm.screen.login.LoginView
import org.labcluster.crm.screen.setting.SettingView
import org.labcluster.crm.screen.topic.TopicView
import org.labcluster.crm.theme.Theme

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

    override fun onResume() {
        super.onResume()
        IntentFilter().apply {
            addAction(Intent.ACTION_TIMEZONE_CHANGED)
            addAction(Intent.ACTION_TIME_CHANGED)
            addAction(Intent.ACTION_DATE_CHANGED)
        }.let { registerReceiver(timeChangedReceiver, it) }
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(timeChangedReceiver)
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
class LessonScreenKey() : NavKey

@Serializable
class TopicScreenKey() : NavKey

@Serializable
class GroupsScreenKey() : NavKey

@Serializable
class CalendarScreenKey() : NavKey

@Serializable
class SettingsScreenKey() : NavKey

@Serializable
class LoginScreenKey() : NavKey

@Composable
fun BoxScope.ScreenContent() {
    val backstack = rememberNavBackStack(GroupsScreenKey())
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    MyNavigationDrawer(backstack, drawerState) {
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
    is LessonScreenKey -> NavEntry(key = key) { LessonView() }
    is TopicScreenKey -> NavEntry(key = key) { TopicView() }
    is GroupsScreenKey -> NavEntry(key = key) { GroupView() }
    is CalendarScreenKey -> NavEntry(key = key) { CalendarView() }
    is SettingsScreenKey -> NavEntry(key = key) { SettingView() }
    is LoginScreenKey -> NavEntry(key = key) { LoginView() }
    else -> throw Error()
}