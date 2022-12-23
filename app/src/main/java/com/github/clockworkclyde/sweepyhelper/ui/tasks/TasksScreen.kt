package com.github.clockworkclyde.sweepyhelper.ui.tasks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.github.clockworkclyde.sweepyhelper.models.ui.base.AppBarState
import com.github.clockworkclyde.sweepyhelper.ui.tasks.contract.TasksViewEvent
import com.github.clockworkclyde.sweepyhelper.ui.tasks.views.TasksViewContentList
import com.github.clockworkclyde.sweepyhelper.ui.tasks.views.TasksViewEmpty
import com.github.clockworkclyde.sweepyhelper.ui.tasks.views.TasksViewLoading

@Composable
fun TasksListScreen(
    viewModel: TasksViewModel,
    roomId: Long,
    onComposing: (AppBarState) -> Unit,
) {
    val viewState = viewModel.viewState.collectAsState()
    val state = viewState.value

    LaunchedEffect(roomId) {
        viewModel.setEvent(TasksViewEvent.EnterScreen(roomId))
        onComposing(AppBarState(state.room.title))
    }

    when {
        state.isError -> TODO()
        state.isLoading -> TasksViewLoading()
        else -> if (state.tasks.isNotEmpty()) {
            TasksViewContentList(
                state = state,
                onTaskClicked = { viewModel.setEvent(TasksViewEvent.OnTaskClicked(it)) })
        } else TasksViewEmpty(
            onFabClicked = { viewModel.setEvent(TasksViewEvent.OnCreateTaskClicked(roomId)) }
        )
    }
}