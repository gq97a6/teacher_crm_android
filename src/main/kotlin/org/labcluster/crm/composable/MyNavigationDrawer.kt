package org.labcluster.crm.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import kotlinx.coroutines.launch
import org.labcluster.crm.CalendarViewKey
import org.labcluster.crm.GroupListViewKey
import org.labcluster.crm.GroupViewKey
import org.labcluster.crm.LessonViewKey
import org.labcluster.crm.SettingViewKey
import org.labcluster.crm.TopicViewKey
import org.labcluster.crm.app.App
import org.labcluster.crm.app.AppState

@Preview
@Composable
private fun Preview() = PreviewScaffold(showAppBar = false) { MyNavigationDrawer() }

@Composable
fun MyNavigationDrawer(
    backstack: NavBackStack<NavKey>? = null,
    drawerState: DrawerState = DrawerState(DrawerValue.Open),
    state: AppState = App.state,
    gesturesEnabled: Boolean = false,
    content: @Composable () -> Unit = {}
) {
    val scope = rememberCoroutineScope()

    val isLessonSet by state.lesson.isLessonSet.collectAsStateWithLifecycle()
    val isTopicSet by state.topic.isTopicSet.collectAsStateWithLifecycle()

    fun onClick(key: NavKey) {
        backstack?.clear()
        backstack?.add(key)
        scope.launch { drawerState.close() }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        content = content,
        gesturesEnabled = gesturesEnabled,
        drawerContent = {
            ModalDrawerSheet(Modifier.fillMaxWidth(.52f)) {
                Spacer(Modifier.weight(1f))
                Icons.Outlined.apply {
                    if (isLessonSet) MyNavigationDrawerItem(
                        selected = backstack?.last() is LessonViewKey,
                        icon = School,
                        label = "Lekcja"
                    ) { onClick(LessonViewKey()) }

                    if (isTopicSet) MyNavigationDrawerItem(
                        selected = backstack?.last() is TopicViewKey,
                        icon = Book,
                        label = "Temat"
                    ) { onClick(TopicViewKey()) }

                    MyNavigationDrawerItem(
                        selected = backstack?.last() is GroupViewKey,
                        icon = Group,
                        label = "Grupy"
                    ) { onClick(GroupListViewKey()) }

                    MyNavigationDrawerItem(
                        selected = backstack?.last() is CalendarViewKey,
                        icon = CalendarMonth,
                        label = "Kalendarz"
                    ) { onClick(CalendarViewKey()) }

                    MyNavigationDrawerItem(
                        selected = backstack?.last() is SettingViewKey,
                        icon = Tune,
                        label = "Ustawienia"
                    ) { onClick(SettingViewKey()) }

                }
                Spacer(Modifier.weight(1f))
            }
        }
    )
}

@Composable
fun MyNavigationDrawerItem(
    selected: Boolean,
    icon: ImageVector,
    label: String,
    onClick: () -> Unit
) {
    NavigationDrawerItem(
        label = { Text(label) },
        selected = selected,
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = label,
                modifier = Modifier,
            )
        },
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .padding(vertical = 5.dp),
        onClick = onClick
    )
}