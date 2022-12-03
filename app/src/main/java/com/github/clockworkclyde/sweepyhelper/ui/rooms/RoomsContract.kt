package com.github.clockworkclyde.sweepyhelper.ui.rooms

import com.github.clockworkclyde.sweepyhelper.ui.base.IViewEffect
import com.github.clockworkclyde.sweepyhelper.ui.base.IViewEvent

sealed class RoomsViewEvent : IViewEvent {
    object LoadRooms : RoomsViewEvent()
}

sealed class RoomsViewEffect : IViewEffect {

}