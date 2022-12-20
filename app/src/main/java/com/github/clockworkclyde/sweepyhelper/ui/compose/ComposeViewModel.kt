package com.github.clockworkclyde.sweepyhelper.ui.compose

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.viewModelScope
import com.github.clockworkclyde.sweepyhelper.domain.usecases.compose.CreateEmptyRoomUseCase
import com.github.clockworkclyde.sweepyhelper.domain.usecases.compose.CreateTasksForRoomUseCase
import com.github.clockworkclyde.sweepyhelper.domain.usecases.compose.GetTaskRegularitiesUseCase
import com.github.clockworkclyde.sweepyhelper.domain.usecases.compose.GetTaskSuggestionsForRoomUseCase
import com.github.clockworkclyde.sweepyhelper.domain.usecases.rooms.GetRoomTypesUseCase
import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.Room
import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.RoomType
import com.github.clockworkclyde.sweepyhelper.models.ui.tasks.Task
import com.github.clockworkclyde.sweepyhelper.ui.base.BaseViewModel
import com.github.clockworkclyde.sweepyhelper.utils.copyToAddNewTask
import com.github.clockworkclyde.sweepyhelper.utils.copyToProgress
import com.github.clockworkclyde.sweepyhelper.utils.copyToSaveLocallySuccess
import com.github.clockworkclyde.sweepyhelper.utils.copyToUpdateTasks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ComposeViewModel(
    private val createEmptyRoom: CreateEmptyRoomUseCase,
    private val createTasksForRoom: CreateTasksForRoomUseCase,
    private val getTaskSuggestionsForRoom: GetTaskSuggestionsForRoomUseCase,
    private val getRoomTypes: GetRoomTypesUseCase,
    private val getTaskRegularities: GetTaskRegularitiesUseCase
) : BaseViewModel<ComposeViewState, ComposeViewEvent, ComposeViewEffect>() {

    private val _suggestions = MutableStateFlow<SnapshotStateList<Task>>(mutableStateListOf())

    val suggestions = _suggestions.asStateFlow()
    val roomTypes by lazy { getRoomTypes() }
    val taskRegularities by lazy { getTaskRegularities() }

    override fun setInitialState(): ComposeViewState {
        return ComposeViewState(
            room = Room(title = "", type = RoomType.NotSelected),
            tasks = listOf(),
            inProgress = false,
            isRoomExisting = false,
            isError = false,
            isDataSaved = false
        )
    }

    override fun handleEvents(event: ComposeViewEvent) {
        when (event) {
            is ComposeViewEvent.RoomTitleChanged -> updateRoom(event.title)
            is ComposeViewEvent.CreateRoomButtonClicked -> createNewRoom(viewState.value.room)
            is ComposeViewEvent.RoomTypeChanged -> updateRoom(event.type)
            is ComposeViewEvent.CreateTaskButtonClicked -> addNewTask(event.task)
            is ComposeViewEvent.FinishComposeClicked -> tryToCopyExistingRoom()
            is ComposeViewEvent.SubmitTasksSuggestions -> setCheckedTaskSuggestions(event.ids)
            is ComposeViewEvent.UpdateTaskSuggestion -> updateTaskSuggestion(event.task)
            is ComposeViewEvent.RequestTaskSuggestionsForRoom -> requestTaskSuggestionsForRoom(event.roomId)
        }
    }

    private fun setCheckedTaskSuggestions(taskId: List<Long>) {
        _suggestions.value.mapNotNull {
            if (taskId.contains(it.id)) {
                it
            } else null
        }.let { updateTasksData(it) }
    }

    private fun requestTaskSuggestionsForRoom(id: Long) {
        getTaskSuggestionsForRoom(id).let {
            val newList = mutableStateListOf<Task>().apply { addAll(it) }
            _suggestions.value = newList
        }
    }

    private fun updateTaskSuggestion(task: Task) {
        _suggestions.value.apply {
            find { it.id == task.id }?.let {
                val index = indexOf(it)
                set(index, task)
            }
        }.let {
            _suggestions.value = it
        }
    }

    private fun updateRoom(title: String) {
        setState { copy(room = room.copy(title = title)) }
    }

    private fun updateRoom(type: RoomType) {
        setState { copy(room = room.copy(type = type)) }
    }

    private fun updateTasksData(tasks: List<Task>) {
        setState { copyToUpdateTasks(tasks) }
    }

    private fun addNewTask(task: Task) {
        setState { copyToAddNewTask(task) }
    }

    private fun createNewRoom(room: Room) {
        setState { copyToProgress() }
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                createEmptyRoom(room)
            }
            setState { copyToSaveLocallySuccess() }
        }
    }

    private fun tryToCopyExistingRoom() {
        currentState.room
            .takeIf { it.title.trim().isNotEmpty() }
            ?.let { copyRoomWithTasks(it, currentState.tasks) }
    }

    private fun copyRoomWithTasks(room: Room, tasks: List<Task>) {
        setState { copyToProgress() }
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                createEmptyRoom(room)
                if (tasks.isNotEmpty()) {
                    createTasksForRoom(tasks)
                }
            }
            setState { copyToSaveLocallySuccess() }
        }
    }
}