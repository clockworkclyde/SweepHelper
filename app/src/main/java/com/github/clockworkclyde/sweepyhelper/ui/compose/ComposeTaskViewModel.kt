package com.github.clockworkclyde.sweepyhelper.ui.compose

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.github.clockworkclyde.sweepyhelper.domain.usecases.compose.CreateTasksForRoomUseCase
import com.github.clockworkclyde.sweepyhelper.domain.usecases.compose.GetTaskRegularitiesUseCase
import com.github.clockworkclyde.sweepyhelper.domain.usecases.compose.GetTaskSuggestionsForRoomUseCase
import com.github.clockworkclyde.sweepyhelper.models.ui.tasks.Regularity
import com.github.clockworkclyde.sweepyhelper.models.ui.tasks.Task
import com.github.clockworkclyde.sweepyhelper.ui.base.BaseViewModel
import com.github.clockworkclyde.sweepyhelper.ui.compose.contract.*
import com.github.clockworkclyde.sweepyhelper.ui.navigation.AppNavigationController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate

class ComposeTaskViewModel(
    private val createTasksForRoom: CreateTasksForRoomUseCase,
    private val getTaskSuggestionsForRoom: GetTaskSuggestionsForRoomUseCase,
    private val getTaskRegularities: GetTaskRegularitiesUseCase,
    private val navigationController: AppNavigationController
) : BaseViewModel<ComposeTaskViewState, ComposeTaskViewEvent, ComposeTaskViewEffect>() {

    private val _suggestions = MutableStateFlow<SnapshotStateList<Task>>(mutableStateListOf())

    val suggestions = _suggestions.asStateFlow()
    val taskRegularities by lazy { getTaskRegularities() }

    override fun setInitialState(): ComposeTaskViewState {
        return ComposeTaskViewState(
            owner = 0L,
            title = "",
            startDate = LocalDate.now(),
            isOnRepeatMode = false,
            regularityQuantity = 3,
            regularity = Regularity.Days,
            tasks = emptyList(),
            suggestions = emptyList(),
            inProgress = false,
            isError = false,
            isDataSaved = false
        )
    }

    override fun handleEvents(event: ComposeTaskViewEvent) {
        when (event) {
            is ComposeTaskViewEvent.CreateTaskButtonClicked -> addNewTask()
            is ComposeTaskViewEvent.FinishComposeClicked -> TODO()
            is ComposeTaskViewEvent.SubmitTasksSuggestions -> setCheckedTaskSuggestions(event.ids)
            is ComposeTaskViewEvent.UpdateTaskSuggestion -> updateTaskSuggestion(event.task)
            is ComposeTaskViewEvent.RequestTaskSuggestionsForRoom -> {
                requestTaskSuggestionsForRoom(event.roomId)
            }
            is ComposeTaskViewEvent.OnRegularityItemChanged -> setState {
                copyToUpdateRegularity(event.regularity)
            }
            is ComposeTaskViewEvent.OnRegularityQuantityChanged -> setState {
                copyToUpdateRegularityQuantity(event.quantity)
            }
            is ComposeTaskViewEvent.OnRepeatModeSwitchedTo -> setState {
                copyToUpdateRepeatMode(event.value)
            }
            is ComposeTaskViewEvent.OnStartDateChanged -> setState { copyToUpdateStartDate(event.date) }
            is ComposeTaskViewEvent.OnTitleChanged -> setState { copyToUpdateOnTitleChanged(event.title) }
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

    private fun updateTasksData(tasks: List<Task>) {
        setState { copyToUpdateSelectedTasks(tasks) }
    }

    private fun addNewTask() {
        val task = currentState.let {
            Task.create(
                title = it.title,
                owner = it.owner,
                startDate = it.startDate,
                regularityQuantity = it.regularityQuantity,
                regularity = it.regularity
            )
        }
        if (!validateTaskFields(task)) {
            // setEffect()
            return
        }
        setState { copyToAddNewTask(task) }
    }

    private fun validateTaskFields(task: Task): Boolean {
        TODO()
    }
}