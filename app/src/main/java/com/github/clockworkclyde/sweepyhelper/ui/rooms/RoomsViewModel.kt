package com.github.clockworkclyde.sweepyhelper.ui.rooms

import androidx.lifecycle.viewModelScope
import com.github.clockworkclyde.sweepyhelper.domain.usecases.rooms.LoadRoomsUseCase
import com.github.clockworkclyde.sweepyhelper.models.base.ItemsViewState
import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.Room
import com.github.clockworkclyde.sweepyhelper.ui.base.BaseViewModel
import com.github.clockworkclyde.sweepyhelper.utils.copyToLoading
import com.github.clockworkclyde.sweepyhelper.utils.copyToSuccess
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class RoomsViewModel(private val loadRoomsUseCase: LoadRoomsUseCase) :
    BaseViewModel<ItemsViewState<List<Room>>, RoomsViewEvent, RoomsViewEffect>() {

    override fun setInitialState(): ItemsViewState<List<Room>> {
        return ItemsViewState(data = emptyList(), isLoading = false, isError = false)
    }

    override fun handleEvents(event: RoomsViewEvent) {
        when (event) {
            is RoomsViewEvent.EnterScreen -> loadRooms()
        }
    }

    private fun loadRooms() {
        loadRoomsUseCase()
            .map { currentState.copyToSuccess(it) }
            .onStart { emit(currentState.copyToLoading()) }
            .onEach { setState { it } }
            .launchIn(viewModelScope)
    }
}