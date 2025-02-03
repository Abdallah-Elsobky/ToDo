package com.example.todo.utils

import com.prolificinteractive.materialcalendarview.CalendarDay
import java.time.LocalDate
import java.time.ZoneId
import java.util.Calendar

val Calendar.year: Int get() = this.get(Calendar.YEAR)


val Calendar.month: Int get() = this.get(Calendar.MONTH)


val Calendar.day: Int get() = this.get(Calendar.DAY_OF_MONTH)

val Calendar.toEpoch: Long
    get() =
        LocalDate.of(this.year, this.month + 1, this.day).atStartOfDay(ZoneId.systemDefault())
            .toEpochSecond() * 1000

val CalendarDay.toEpoch: Long
    get() =
        LocalDate.of(this.year, this.month, this.day).atStartOfDay(ZoneId.systemDefault())
            .toEpochSecond() * 1000

val Calendar.toTime: String
    get() = this.get(Calendar.HOUR_OF_DAY).toString() + ":" + this.get(
        Calendar.MINUTE
    ).toString()