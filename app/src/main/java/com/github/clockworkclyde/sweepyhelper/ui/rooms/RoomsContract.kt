package com.github.clockworkclyde.sweepyhelper.ui.rooms

import com.github.clockworkclyde.sweepyhelper.ui.base.ViewEffect
import com.github.clockworkclyde.sweepyhelper.ui.base.ViewEvent

sealed class RoomsViewEvent : ViewEvent {
    object LoadRooms : RoomsViewEvent()
}

sealed class RoomsViewEffect : ViewEffect {

}