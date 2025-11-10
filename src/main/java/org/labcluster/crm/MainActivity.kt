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
import org.labcluster.crm.screens.CalendarScreen
import org.labcluster.crm.screens.CourseScreen
import org.labcluster.crm.screens.GroupsScreen
import org.labcluster.crm.screens.LessonScreen
import org.labcluster.crm.screens.LoginScreen
import org.labcluster.crm.screens.ReportScreen
import org.labcluster.crm.screens.SettingsScreen
import org.labcluster.crm.ui.components.MyNavigationDrawer
import org.labcluster.crm.utils.PreviewSample
import org.labcluster.crm.utils.composeConstruct

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
    is LessonScreenKey -> NavEntry(key = key) { LessonScreen() }
    is CourseScreenKey -> NavEntry(key = key) { CourseScreen() }
    is GroupsScreenKey -> NavEntry(key = key) { GroupsScreen() }
    is CalendarScreenKey -> NavEntry(key = key) { CalendarScreen() }
    is ReportScreenKey -> NavEntry(key = key) { ReportScreen() }
    is SettingsScreenKey -> NavEntry(key = key) { SettingsScreen() }
    is LoginScreenKey -> NavEntry(key = key) { LoginScreen() }
    else -> throw Error()
}