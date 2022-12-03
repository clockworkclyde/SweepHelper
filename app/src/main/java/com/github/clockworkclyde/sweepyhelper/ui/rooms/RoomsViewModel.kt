package com.github.clockworkclyde.sweepyhelper.ui.rooms

import androidx.lifecycle.viewModelScope
import com.github.clockworkclyde.sweepyhelper.domain.usecases.rooms.LoadRoomsUseCase
import com.github.clockworkclyde.sweepyhelper.models.base.ViewState
import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.Room
import com.github.clockworkclyde.sweepyhelper.ui.base.BaseViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class RoomsViewModel(private val loadRoomsUseCase: LoadRoomsUseCase) :
    BaseViewModel<ViewState<List<Room>>, RoomsViewEvent, RoomsViewEffect>() {

    override fun setInitialState(): ViewState<List<Room>> {
        return ViewState.Empty
    }

    override fun handleEvents(event: RoomsViewEvent) {
        when (event) {
            is RoomsViewEvent.LoadRooms -> loadRooms()
        }
    }

    private fun loadRooms() {
        loadRoomsUseCase()
            .map { rooms ->
                if (rooms.isNotEmpty()) {
                    ViewState.Success(rooms)
                } else {
                    ViewState.Empty
                }
            }
            .onStart { emit(ViewState.Loading) }
            .onEach { setState(it) }
            .launchIn(viewModelScope)
    }
}