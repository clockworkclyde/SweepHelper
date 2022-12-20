package com.github.clockworkclyde.sweepyhelper.utils

import timber.log.Timber
import java.time.LocalDate
import java.time.format.DateTimeFormatter

// Data extensions

fun String.matchesNumbersOnly() = matches("[a-zA-Z]+".toRegex()).not()

private val dateFormatter by lazy { (DateTimeFormatter.ofPattern("dd-MM-yyyy")) }

fun LocalDate.formatByDefaultDateFormatter(): String = format(dateFormatter)


// Logging extensions

inline fun logg(crossinline onText: () -> String) {
    Timber.e(onText())
}

inline fun loggError(crossinline onThrowable: () -> Throwable) {
    Timber.e(onThrowable())
}
