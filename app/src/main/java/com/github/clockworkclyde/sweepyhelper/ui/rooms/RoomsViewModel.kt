package com.github.clockworkclyde.sweepyhelper.ui.rooms

import androidx.lifecycle.viewModelScope
import com.github.clockworkclyde.sweepyhelper.domain.usecases.rooms.LoadRoomsUseCase
import com.github.clockworkclyde.sweepyhelper.ui.base.BaseViewModel
import com.github.clockworkclyde.sweepyhelper.ui.navigation.ComposeRoom
import com.github.clockworkclyde.sweepyhelper.ui.navigation.DestinationManager
import com.github.clockworkclyde.sweepyhelper.ui.navigation.RoomsFlow
import com.github.clockworkclyde.sweepyhelper.ui.rooms.contract.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class RoomsViewModel(
    private val loadRoomsUseCase: LoadRoomsUseCase,
    private val destinationManager: DestinationManager
) :
    BaseViewModel<RoomsViewState, RoomsViewEvent, RoomsViewEffect>() {

    override fun setInitialState(): RoomsViewState {
        return RoomsViewState(rooms = emptyList(), isLoading = false, isError = false)
    }

    override fun handleEvents(event: RoomsViewEvent) {
        when (event) {
            is RoomsViewEvent.EnterScreen -> {
                loadRooms()
            }
            is RoomsViewEvent.OnCreateNewRoomClicked -> {
                destinationManager.setDestination(ComposeRoom)
            }
            is RoomsViewEvent.OnRoomClicked -> {
                destinationManager.setDestination(RoomsFlow.RoomTasks(event.room.id))
            }
        }
    }

    private fun loadRooms() {
        loadRoomsUseCase()
            .map { currentState().copyToSuccess(it) }
            .onStart { emit(currentState().copyToLoading()) }
            .onEach { setState { it } }
            .launchIn(viewModelScope)
    }
}