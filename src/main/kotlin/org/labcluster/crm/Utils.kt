package org.labcluster.crm

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.Month
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime
import org.labcluster.crm.app.App.Companion.state
import org.labcluster.crm.objects.Mock
import org.labcluster.crm.theme.Theme
import org.labcluster.crm.theme.darkColorScheme
import kotlin.time.Clock

fun ComponentActivity.composeConstruct(
    isDark: Boolean = true,
    content: @Composable () -> Unit
) {
    setContent {
        Theme(isDark) {
            Box(
                Modifier
                    .imePadding()
                    .fillMaxSize()
                    .background(cs.background)
            ) {
                content()
            }
        }
    }
}

val cs: ColorScheme
    @Composable
    get() = MaterialTheme.colorScheme

@Composable
fun PreviewSample(scheme: ColorScheme = darkColorScheme, content: @Composable BoxScope.() -> Unit) {
    //Mock app state
    state = Mock.state

    Theme(scheme) {
        Box(
            Modifier
                .fillMaxSize()
                .background(cs.background)
        ) {
            content()
        }
    }
}

val dayFormat = LocalDateTime.Format {
    dayOfWeek(
        DayOfWeekNames(
            "Poniedziałek",
            "Wtorek",
            "Środa",
            "Czwartek",
            "Piątek",
            "Sobota",
            "Niedziela"
        )
    )
}

val shortDateFormat = LocalDateTime.Format {
    day()
    char('.')
    monthNumber()
}

val dateFormat = LocalDateTime.Format {
    day()
    char('.')
    monthNumber()
    char('.')
    year()
}

val timeFormat = LocalDateTime.Format {
    hour()
    char(':')
    minute()
}

val months = listOf(
    "Styczeń",
    "Luty",
    "Marzec",
    "Kwiecień",
    "Maj",
    "Czerwiec",
    "Lipiec",
    "Sierpień",
    "Wrzesień",
    "Październik",
    "Listopad",
    "Grudzień"
)

val currentDateTime = Clock.System.now().toLocalDateTime(state.timeZone.value)

fun Month.format() = months[this.ordinal]