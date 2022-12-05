package com.github.clockworkclyde.sweepyhelper.ui.compose

import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.RoomType
import com.github.clockworkclyde.sweepyhelper.ui.base.IViewEffect
import com.github.clockworkclyde.sweepyhelper.ui.base.IViewEvent
import com.github.clockworkclyde.sweepyhelper.ui.base.IViewState

data class ComposeViewState<out C : Any, T : List<Any>>(
    val room: C,
    val tasks: T,
    val inProgress: Boolean,
    val isRoomExisting: Boolean,
    val isError: Boolean = false
) : IViewState

sealed class ComposeViewEvent : IViewEvent {
    data class RoomTitleChanged(val title: String) : ComposeViewEvent()
    data class RoomTypeChanged(val type: RoomType) : ComposeViewEvent()
    object SaveButtonClicked : ComposeViewEvent()
}

sealed class ComposeViewEffect : IViewEffect {

}