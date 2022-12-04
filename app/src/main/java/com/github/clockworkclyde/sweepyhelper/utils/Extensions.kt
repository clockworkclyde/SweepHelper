package com.github.clockworkclyde.sweepyhelper.utils

import com.github.clockworkclyde.sweepyhelper.models.base.ItemsViewState
import com.github.clockworkclyde.sweepyhelper.ui.compose.ComposeViewState

// Items View State

fun <T> ItemsViewState<T>.copyToLoading() = copy(isLoading = true, isError = false)

fun <T> ItemsViewState<T>.copyToSuccess(items: T) = copy(data = items, isLoading = false)

// Compose View State

fun <C : Any, T : List<Any>> ComposeViewState<C, T>.copyToError() = copy(isError = true)

fun <C : Any, T : List<Any>> ComposeViewState<C, T>.copyToProgress() =
    copy(isError = false, inProgress = true)

fun <C : Any, T : List<Any>> ComposeViewState<C, T>.copyToSaveRoomSuccess(room: C, tasks: T) =
    copy(room = room, tasks = tasks, isError = false, inProgress = false, isRoomExisting = true)

fun <C : Any, T : List<Any>> ComposeViewState<C, T>.copyToUpdateRoom(room: C) =
    copy(room = room)

fun <C : Any, T : List<Any>> ComposeViewState<C, T>.copyToUpdateTasks(tasks: T) =
    copy(tasks = tasks)
