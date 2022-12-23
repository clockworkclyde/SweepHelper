package com.github.clockworkclyde.sweepyhelper.ui.compose.contract

import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.RoomType
import com.github.clockworkclyde.sweepyhelper.models.ui.tasks.Regularity
import com.github.clockworkclyde.sweepyhelper.models.ui.tasks.Task
import java.time.LocalDate

/** TaskViewState **/
fun ComposeTaskViewState.copyToError() = copy(isError = true)

fun ComposeTaskViewState.copyToUpdateRegularity(regularity: Regularity) =
    copy(regularity = regularity)

fun ComposeTaskViewState.copyToUpdateRegularityQuantity(quantity: Int) =
    copy(regularityQuantity = quantity)

fun ComposeTaskViewState.copyToUpdateRepeatMode(value: Boolean) =
    copy(isOnRepeatMode = value)

fun ComposeTaskViewState.copyToUpdateStartDate(date: LocalDate) = copy(startDate = date)

fun ComposeTaskViewState.copyToUpdateOnTitleChanged(title: String) = copy(title = title)

fun ComposeTaskViewState.copyToProgress() = copy(isError = false, inProgress = true)

fun ComposeTaskViewState.copyToUpdateSelectedTasks(tasks: List<Task>) = copy(tasks = tasks)

fun ComposeTaskViewState.copyToAddNewTask(task: Task) =
    copy(tasks = tasks
        .toMutableList()
        .apply { add(task) })


/** RoomViewState **/

fun ComposeRoomViewState.copyToError() = copy(isError = true)

fun ComposeRoomViewState.copyToProgress() =
    copy(isError = false, inProgress = true)

fun ComposeRoomViewState.copyToUpdateRoomTitle(title: String) = copy(title = title)

fun ComposeRoomViewState.copyToUpdateRoomType(type: RoomType) = copy(type = type)

//fun ComposeRoomViewState.copyToSaveRoomSuccess(room: Room) =
//    copy(title = room.title, type = room.type, isError = false, inProgress = false)

fun ComposeRoomViewState.copyToSaveLocallySuccess() =
    copy(isDataSaved = true, inProgress = false)
