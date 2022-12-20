package com.github.clockworkclyde.sweepyhelper.ui.rooms

import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.Room
import com.github.clockworkclyde.sweepyhelper.ui.base.IViewEffect
import com.github.clockworkclyde.sweepyhelper.ui.base.IViewEvent

sealed class RoomsViewEvent : IViewEvent {
    object EnterScreen : RoomsViewEvent()
    object OnCreateNewRoomClicked : RoomsViewEvent()
    data class OnRoomClicked(val room: Room) : RoomsViewEvent()
}

sealed class RoomsViewEffect : IViewEffect {

}