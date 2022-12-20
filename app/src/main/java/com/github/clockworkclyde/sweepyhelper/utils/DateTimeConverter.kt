package com.github.clockworkclyde.sweepyhelper.utils

import com.google.gson.Gson
import java.time.LocalDate

object DateTimeConverter {

    private val gson = Gson()

    fun stringToDate(source: String): LocalDate {
        return gson.fromJson(source, LocalDate::class.java)
    }

    fun dateToString(date: LocalDate): String {
        return gson.toJson(date)
    }
}