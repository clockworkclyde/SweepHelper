package com.github.clockworkclyde.sweepyhelper.ui.compose

import androidx.lifecycle.viewModelScope
import com.github.clockworkclyde.sweepyhelper.domain.usecases.compose.CreateEmptyRoomUseCase
import com.github.clockworkclyde.sweepyhelper.domain.usecases.rooms.GetRoomTypesUseCase
import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.Room
import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.RoomType
import com.github.clockworkclyde.sweepyhelper.models.ui.tasks.Task
import com.github.clockworkclyde.sweepyhelper.ui.base.BaseViewModel
import com.github.clockworkclyde.sweepyhelper.utils.copyToProgress
import com.github.clockworkclyde.sweepyhelper.utils.copyToSaveRoomSuccess
import com.github.clockworkclyde.sweepyhelper.utils.copyToUpdateTasks
import kotlinx.coroutines.launch

class ComposeViewModel(
    private val createEmptyRoom: CreateEmptyRoomUseCase,
    private val getRoomTypes: GetRoomTypesUseCase
) : BaseViewModel<ComposeViewState<Room, List<Task>>, ComposeViewEvent, ComposeViewEffect>() {

    val roomTypes by lazy { getRoomTypes() }

    override fun setInitialState(): ComposeViewState<Room, List<Task>> {
        return ComposeViewState(
            room = Room(title = "", type = RoomType.NotSelected),
            tasks = emptyList(),
            inProgress = false,
            isRoomExisting = false,
            isError = false
        )
    }

    override fun handleEvents(event: ComposeViewEvent) {
        when (event) {
            is ComposeViewEvent.RoomTitleChanged -> updateRoom(event.title)
            is ComposeViewEvent.SaveButtonClicked -> createNewRoom(viewState.value.room)
            is ComposeViewEvent.RoomTypeChanged -> updateRoom(event.type)
        }
    }

    private fun updateRoom(title: String) {
        setState { copy(room.copy(title = title)) }
    }

    private fun updateRoom(type: RoomType) {
        setState { copy(room.copy(type = type)) }
    }

    private fun updateTasksData(tasks: List<Task>) {
        setState {
            copyToUpdateTasks(tasks)
        }
    }

    private fun createNewRoom(room: Room) {
        setState { copyToProgress() }
        viewModelScope.launch {
            createEmptyRoom(room)
            setState { copyToSaveRoomSuccess(room, emptyList()) }
        }
    }
}