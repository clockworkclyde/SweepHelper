package com.github.clockworkclyde.sweepyhelper.ui.compose

import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.Room
import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.RoomType
import com.github.clockworkclyde.sweepyhelper.models.ui.tasks.Task
import com.github.clockworkclyde.sweepyhelper.ui.base.IViewEffect
import com.github.clockworkclyde.sweepyhelper.ui.base.IViewEvent
import com.github.clockworkclyde.sweepyhelper.ui.base.IViewState

data class ComposeViewState(
    val room: Room,
    val tasks: List<Task>,
    val inProgress: Boolean,
    val isRoomExisting: Boolean,
    val isError: Boolean,
    val isDataSaved: Boolean
) : IViewState

sealed class ComposeViewEvent : IViewEvent {
    data class RoomTitleChanged(val title: String) : ComposeViewEvent()
    data class RoomTypeChanged(val type: RoomType) : ComposeViewEvent()
    data class CreateTaskButtonClicked(val task: Task) : ComposeViewEvent()
    data class RequestTaskSuggestionsForRoom(val roomId: Long) : ComposeViewEvent()
    data class UpdateTaskSuggestion(val task: Task) : ComposeViewEvent()
    data class SubmitTasksSuggestions(val ids: List<Long>) : ComposeViewEvent()
    object CreateRoomButtonClicked : ComposeViewEvent()
    object FinishComposeClicked : ComposeViewEvent()
}

sealed class ComposeViewEffect : IViewEffect {

}