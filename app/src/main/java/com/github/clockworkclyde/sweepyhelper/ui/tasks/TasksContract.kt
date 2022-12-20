package com.github.clockworkclyde.sweepyhelper.ui.tasks

import com.github.clockworkclyde.sweepyhelper.models.ui.tasks.Task
import com.github.clockworkclyde.sweepyhelper.ui.base.IViewEffect
import com.github.clockworkclyde.sweepyhelper.ui.base.IViewEvent
import com.github.clockworkclyde.sweepyhelper.ui.base.IViewState

data class TasksViewState(val tasks: List<Task>) : IViewState

sealed class TasksViewEvent : IViewEvent {

}

sealed class TasksViewEffect : IViewEffect {

}