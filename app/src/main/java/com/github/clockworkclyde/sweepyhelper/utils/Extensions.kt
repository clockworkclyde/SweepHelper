package com.github.clockworkclyde.sweepyhelper.utils

import com.github.clockworkclyde.sweepyhelper.models.base.ItemsViewState
import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.Room
import com.github.clockworkclyde.sweepyhelper.models.ui.tasks.Task
import com.github.clockworkclyde.sweepyhelper.ui.compose.ComposeViewState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

// Items View State

fun <T> ItemsViewState<T>.copyToLoading() = copy(isLoading = true, isError = false)

fun <T> ItemsViewState<T>.copyToSuccess(items: T) = copy(data = items, isLoading = false)

// Compose View State

fun ComposeViewState.copyToError() = copy(isError = true)

fun ComposeViewState.copyToProgress() =
    copy(isError = false, inProgress = true)

fun ComposeViewState.copyToSaveRoomSuccess(room: Room) =
    copy(room = room, isError = false, inProgress = false, isRoomExisting = true)

fun ComposeViewState.copyToUpdateRoom(room: Room) =
    copy(room = room)

fun ComposeViewState.copyToUpdateTasks(tasks: List<Task>) =
    copy(tasks = tasks)

fun ComposeViewState.copyToSaveLocallySuccess() =
    copy(isDataSaved = true, inProgress = false)

fun ComposeViewState.copyToAddNewTask(task: Task) =
    copy(tasks = tasks
        .toMutableList()
        .apply { add(task) })

// Data extensions

fun String.matchesNumbersOnly() = matches("[a-zA-Z]+".toRegex()).not()

// todo remove extensions for string class
fun String.checkIsRegularityQuantityValid(): String? {
    return if (length <= 2) {
        takeIf { matchesNumbersOnly() }
    } else null
}

private val dateFormatter by lazy { (DateTimeFormatter.ofPattern("dd-MM-yyyy")) }

fun LocalDate.formatByDefaultDateFormatter(): String = format(dateFormatter)