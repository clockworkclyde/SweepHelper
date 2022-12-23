package com.github.clockworkclyde.sweepyhelper.ui.compose.contract

import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.RoomType
import com.github.clockworkclyde.sweepyhelper.ui.base.IViewEffect
import com.github.clockworkclyde.sweepyhelper.ui.base.IViewEvent
import com.github.clockworkclyde.sweepyhelper.ui.base.IViewState

data class ComposeRoomViewState(
    val title: String,
    val type: RoomType,
    val inProgress: Boolean,
    val isError: Boolean,
    val isDataSaved: Boolean
) : IViewState

sealed class ComposeRoomViewEvent : IViewEvent {
    data class RoomTitleChanged(val title: String) : ComposeRoomViewEvent()
    data class RoomTypeChanged(val type: RoomType) : ComposeRoomViewEvent()
    object CreateRoomButtonClicked : ComposeRoomViewEvent()
}

sealed class ComposeRoomViewEffect : IViewEffect {
    object TitleValidationFailure : ComposeRoomViewEffect()
}