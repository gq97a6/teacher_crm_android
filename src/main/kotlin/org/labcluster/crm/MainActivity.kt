package org.labcluster.crm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import kotlinx.serialization.Serializable
import org.labcluster.crm.view.CalendarView
import org.labcluster.crm.view.CourseView
import org.labcluster.crm.view.GroupView
import org.labcluster.crm.view.LessonView
import org.labcluster.crm.view.LoginView
import org.labcluster.crm.view.ReportView
import org.labcluster.crm.view.SettingView
import org.labcluster.crm.composable.shared.MyNavigationDrawer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        actionBar?.hide()
        composeConstruct {
            ScreenContent()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() = PreviewSample { ScreenContent() }

@Serializable
class LessonScreenKey() : NavKey

@Serializable
class CourseScreenKey() : NavKey

@Serializable
class GroupsScreenKey() : NavKey

@Serializable
class CalendarScreenKey() : NavKey

@Serializable
class ReportScreenKey() : NavKey

@Serializable
class SettingsScreenKey() : NavKey

@Serializable
class LoginScreenKey() : NavKey

@Composable
fun ScreenContent() {
    val backstack = rememberNavBackStack(CourseScreenKey())

    MyNavigationDrawer(backstack) {
        NavDisplay(
            backStack = backstack,
            entryDecorators = listOf(
                rememberSceneSetupNavEntryDecorator(),
                rememberSavedStateNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
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

fun <T : Any> entryProvider(key: T): NavEntry<NavKey> = when (key) {
    is LessonScreenKey -> NavEntry(key = key) { LessonView() }
    is CourseScreenKey -> NavEntry(key = key) { CourseView() }
    is GroupsScreenKey -> NavEntry(key = key) { GroupView() }
    is CalendarScreenKey -> NavEntry(key = key) { CalendarView() }
    is ReportScreenKey -> NavEntry(key = key) { ReportView() }
    is SettingsScreenKey -> NavEntry(key = key) { SettingView() }
    is LoginScreenKey -> NavEntry(key = key) { LoginView() }
    else -> throw Error()
}