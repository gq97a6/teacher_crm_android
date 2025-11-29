package org.labcluster.crm.android

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import kotlinx.serialization.KSerializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

val cs: ColorScheme
    @Composable
    get() = MaterialTheme.colorScheme

val polishDayOfWeekNames = DayOfWeekNames(
    "Poniedziałek",
    "Wtorek",
    "Środa",
    "Czwartek",
    "Piątek",
    "Sobota",
    "Niedziela"
)

val dayFormat = LocalDateTime.Format {
    dayOfWeek(polishDayOfWeekNames)
}

val polishDayOfWeekShortNames = DayOfWeekNames(
    "Pon",
    "Wto",
    "Śro",
    "Czw",
    "Pią",
    "Sob",
    "Nie"
)

val shortDayFormat = LocalDateTime.Format {
    dayOfWeek(polishDayOfWeekShortNames)
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

class StateFlowSerializer<T>(private val s: KSerializer<T>) : KSerializer<MutableStateFlow<T>> {
    override val descriptor = s.descriptor
    override fun serialize(encoder: Encoder, value: MutableStateFlow<T>) =
        s.serialize(encoder, value.value)

    override fun deserialize(decoder: Decoder) = MutableStateFlow(s.deserialize(decoder))
}