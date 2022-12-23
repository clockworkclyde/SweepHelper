package com.github.clockworkclyde.sweepyhelper.ui.rooms.contract

import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.Room

fun RoomsViewState.copyToLoading() = copy(isLoading = true, isError = false)

fun RoomsViewState.copyToSuccess(items: List<Room>) = copy(rooms = items, isLoading = false)