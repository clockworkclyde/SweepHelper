package com.github.clockworkclyde.sweepyhelper.ui.rooms.contract

import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.Room
import com.github.clockworkclyde.sweepyhelper.ui.base.IViewEffect
import com.github.clockworkclyde.sweepyhelper.ui.base.IViewEvent
import com.github.clockworkclyde.sweepyhelper.ui.base.IViewState

data class RoomsViewState(
    val rooms: List<Room>,
    val isLoading: Boolean,
    val isError: Boolean
) : IViewState

sealed class RoomsViewEvent : IViewEvent {
    object EnterScreen : RoomsViewEvent()
    object OnCreateNewRoomClicked : RoomsViewEvent()
    data class OnRoomClicked(val room: Room) : RoomsViewEvent()
}

sealed class RoomsViewEffect : IViewEffect {

}