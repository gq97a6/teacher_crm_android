package org.labcluster.crm

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char

val cs: ColorScheme
    @Composable
    get() = MaterialTheme.colorScheme

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

val monthFormat = LocalDate.Format {
    monthName(
        MonthNames(
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