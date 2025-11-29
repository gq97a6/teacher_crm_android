package org.labcluster.crm.android.screen.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import org.labcluster.crm.android.LessonViewKey
import org.labcluster.crm.android.app.App
import org.labcluster.crm.android.app.AppState
import org.labcluster.crm.android.monthFormat
import org.labcluster.crm.shared.Open
import org.labcluster.crm.shared.model.Lesson
import org.labcluster.crm.shared.timeStart

@Open
class CalendarViewModel(val state: AppState = App.state) : ViewModel() {

    val isLoadingShown = MutableStateFlow(false)
    val isLegendShown = MutableStateFlow(false)
    val date = MutableStateFlow(LocalDate.fromEpochDays(1))
    val clock = state.chronos.clock(viewModelScope)

    //Create filtered lessons state that is dependent on multiple flows
    val lessons: StateFlow<List<Lesson>> = combine(
        state.calendar.lessons,
        date,
        state.chronos.timeZone
    ) { lessonsList, selectedDate, currentTimeZone ->
        lessonsList.filter { lesson ->
            lesson.timeStart(currentTimeZone).let {
                it.month == selectedDate.month && it.year == selectedDate.year
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    //Create title state that is dependent on selected date
    val title: StateFlow<String> = date.map {
        if (it.year == clock.value.year) it.format(monthFormat)
        else it.format(monthFormat) + " " + it.year
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )

    init {
        date.value = clock.value.date
    }

    fun onLegendClicked() {
        isLegendShown.value = true
    }

    fun onRefreshClicked() {
        viewModelScope.launch {
            val timerJob = launch { delay(1000) }
            isLoadingShown.value = true

            state.alter(viewModelScope) { calendar.fetch() }

            timerJob.join()
            isLoadingShown.value = false
        }
    }

    fun onCloseLegend() {
        isLegendShown.value = false
    }

    fun onLessonClicked(lessonClicked: Lesson) {
        state.alter {
            lesson.setLesson(lessonClicked)
            state.backstack.value.add(LessonViewKey())
        }
    }

    fun onPreviousClicked() {
        date.value = date.value.minus(1, DateTimeUnit.MONTH)
    }

    fun onCurrentSelected() {
        date.value = clock.value.date
    }

    fun onNextClicked() {
        date.value = date.value.plus(1, DateTimeUnit.MONTH)
    }
}