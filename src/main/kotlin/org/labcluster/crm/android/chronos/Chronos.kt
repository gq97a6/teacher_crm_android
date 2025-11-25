package org.labcluster.crm.android.chronos

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

class Chronos {
    //Always returns current time zone
    val timeZone = MutableStateFlow(TimeZone.currentSystemDefault())

    //Always returns current time
    val currentTime: LocalDateTime
        get() = Clock.System.now().toLocalDateTime(timeZone.value)

    //Emits new value every second
    fun clock(scope: CoroutineScope) = flow {
        while (currentCoroutineContext().isActive) {
            emit(currentTime)
            delay(1000)
        }
    }.stateIn(
        scope = scope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = Clock.System.now().toLocalDateTime(timeZone.value)
    )

    //Always current time
    //Emits on access or when notified
    val awareClock = MutableStateFlow(currentTime)
        get() = field.apply { value = currentTime }

    fun notifyTimeChanged() {
        timeZone.value = TimeZone.currentSystemDefault()
        awareClock.value = currentTime
    }
}