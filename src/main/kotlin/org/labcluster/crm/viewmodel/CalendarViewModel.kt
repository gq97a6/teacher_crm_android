package org.labcluster.crm.viewmodel

import android.app.Application
import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import org.labcluster.crm.app.App
import org.labcluster.crm.app.AppState
import org.labcluster.crm.shared.model.Lesson

class CalendarViewModel(
    app: Application = Application(),
    state: AppState = App.state
) : ViewModel() {
    val startDate = MutableStateFlow(TextFieldState(""))
    val endDate = MutableStateFlow(TextFieldState(""))
    val calendar = MutableStateFlow(listOf<List<String>>())
    val appBarTitle = MutableStateFlow("Marzec | Kwiecień")
    val appBarSubtitle = MutableStateFlow("Marzec | Kwiecień")

    fun onSearchClick() {
    }

    fun asCalendarList(day: LocalDate, lessons: List<Lesson>): List<String> {
        val dayFormat = LocalDate.Format {
            monthNumber()
            char('.')
            day()
            char('.')
            year()
        }
        val dayTimestamp = day.format(dayFormat) //eg. 01.01.2025
        val dayOfWeek = listOf(
            "Poniedziałek",
            "Wtorek",
            "Środa",
            "Czwartek",
            "Piątek",
            "Sobota",
            "Niedziela"
        )[day.dayOfWeek.ordinal]

        LocalDateTime.Format {
            hour()
            char(':')
            minute()
        }

        //val lessonsTimestamps = lessons.map {
        //    "${it.timeStart.format(timeFormat)} - ${it.timeEnd.format(timeFormat)}"
        //}

        return listOf(dayTimestamp, dayOfWeek)
    }
}