package com.github.clockworkclyde.sweepyhelper.utils

import com.google.gson.Gson
import org.joda.time.DateTime

class DateTimeConverter {

    companion object {
        private val gson = Gson()


        fun stringToDate(source: String): DateTime {
            return gson.fromJson(source, DateTime::class.java)
        }

        fun dateToString(date: DateTime): String {
            return gson.toJson(date)
        }
    }
}