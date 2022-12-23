package com.github.clockworkclyde.sweepyhelper.ui.tasks

import androidx.lifecycle.viewModelScope
import com.github.clockworkclyde.sweepyhelper.domain.usecases.rooms.LoadRoomByIdUseCase
import com.github.clockworkclyde.sweepyhelper.domain.usecases.tasks.LoadTasksByRoomIdUseCase
import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.Room
import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.RoomType
import com.github.clockworkclyde.sweepyhelper.models.ui.tasks.Task
import com.github.clockworkclyde.sweepyhelper.ui.base.BaseViewModel
import com.github.clockworkclyde.sweepyhelper.ui.navigation.ComposeTaskSuggestions
import com.github.clockworkclyde.sweepyhelper.ui.navigation.DestinationManager
import com.github.clockworkclyde.sweepyhelper.ui.navigation.RoomsFlow
import com.github.clockworkclyde.sweepyhelper.ui.tasks.contract.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TasksViewModel(
    private val loadRoom: LoadRoomByIdUseCase,
    private val loadTasksForRoom: LoadTasksByRoomIdUseCase,
    private val destinationManager: DestinationManager
) :
    BaseViewModel<TasksViewState, TasksViewEvent, TasksViewEffect>() {

    override fun setInitialState(): TasksViewState {
        return TasksViewState(
            tasks = emptyList(),
            room = Room.create("", RoomType.NotSelected),
            isLoading = false,
            isError = false
        )
    }

    override fun handleEvents(event: TasksViewEvent) {
        when (event) {
            is TasksViewEvent.EnterScreen -> {
                loadRoomWithTasks(event.roomId)
            }
            is TasksViewEvent.OnTaskClicked -> {
                handleTaskClicked(event.task.id)
            }
            is TasksViewEvent.OnCreateTaskClicked -> {
                navigateToComposeTasks(event.roomId)
            }
        }
    }

    private fun loadRoomWithTasks(roomId: Long) {
        setState { copyToLoading() }
        viewModelScope.launch {
            val tasks = mutableListOf<Task>()
            withContext(Dispatchers.IO) {
                loadRoom(roomId)
                    .also { tasks.addAll(loadTasksForRoom(it.id)) }
                    .let { setState { copyToUpdateRoom(it) } }
            }
            setState { copyToUpdateTasks(tasks) }
        }
    }

    private fun handleTaskClicked(id: Long) {
        destinationManager.setDestination(RoomsFlow.TaskDetails(id))
    }

    private fun navigateToComposeTasks(id: Long) {
        destinationManager.setDestination(ComposeTaskSuggestions(id))
    }
}

