package com.github.clockworkclyde.sweepyhelper.ui.compose.contract

import com.github.clockworkclyde.sweepyhelper.models.ui.tasks.Regularity
import com.github.clockworkclyde.sweepyhelper.models.ui.tasks.Task
import com.github.clockworkclyde.sweepyhelper.ui.base.IViewEffect
import com.github.clockworkclyde.sweepyhelper.ui.base.IViewEvent
import com.github.clockworkclyde.sweepyhelper.ui.base.IViewState
import java.time.LocalDate

data class ComposeTaskViewState(
    val owner: Long,
    val title: String,
    val startDate: LocalDate,
    val isOnRepeatMode: Boolean,
    val regularityQuantity: Int,
    val regularity: Regularity,
    val tasks: List<Task>,
    val suggestions: List<Task>,
    val inProgress: Boolean,
    val isError: Boolean,
    val isDataSaved: Boolean
) : IViewState

sealed class ComposeTaskViewEvent : IViewEvent {
    data class OnTitleChanged(val title: String) : ComposeTaskViewEvent()
    data class OnRepeatModeSwitchedTo(val value: Boolean) : ComposeTaskViewEvent()
    data class OnStartDateChanged(val date: LocalDate) : ComposeTaskViewEvent()
    data class OnRegularityQuantityChanged(val quantity: Int) : ComposeTaskViewEvent()
    data class OnRegularityItemChanged(val regularity: Regularity) : ComposeTaskViewEvent()
    data class RequestTaskSuggestionsForRoom(val roomId: Long) : ComposeTaskViewEvent()
    data class UpdateTaskSuggestion(val task: Task) : ComposeTaskViewEvent()
    data class SubmitTasksSuggestions(val ids: List<Long>) : ComposeTaskViewEvent()
    object FinishComposeClicked : ComposeTaskViewEvent()
    object CreateTaskButtonClicked : ComposeTaskViewEvent()
}

sealed class ComposeTaskViewEffect : IViewEffect {

}