package com.github.clockworkclyde.sweepyhelper.ui.tasks.contract

import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.Room
import com.github.clockworkclyde.sweepyhelper.models.ui.tasks.Task

fun TasksViewState.copyToLoading() = copy(isLoading = true, isError = false)
fun TasksViewState.copyToError() = copy(isLoading = false, isError = true)
fun TasksViewState.copyToUpdateTasks(tasks: List<Task>) = copy(tasks = tasks, isLoading = false)
fun TasksViewState.copyToUpdateRoom(room: Room) = copy(room = room)