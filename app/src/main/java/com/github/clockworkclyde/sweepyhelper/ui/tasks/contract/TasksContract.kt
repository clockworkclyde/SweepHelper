package com.github.clockworkclyde.sweepyhelper.ui.tasks.contract

import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.Room
import com.github.clockworkclyde.sweepyhelper.models.ui.tasks.Task
import com.github.clockworkclyde.sweepyhelper.ui.base.IViewEffect
import com.github.clockworkclyde.sweepyhelper.ui.base.IViewEvent
import com.github.clockworkclyde.sweepyhelper.ui.base.IViewState

data class TasksViewState(
    val tasks: List<Task>,
    val room: Room,
    val isLoading: Boolean,
    val isError: Boolean,
) : IViewState

sealed class TasksViewEvent : IViewEvent {
    data class EnterScreen(val roomId: Long) : TasksViewEvent()
    data class OnTaskClicked(val task: Task) : TasksViewEvent()
    data class OnCreateTaskClicked(val roomId: Long) : TasksViewEvent()
}

sealed class TasksViewEffect : IViewEffect {

}