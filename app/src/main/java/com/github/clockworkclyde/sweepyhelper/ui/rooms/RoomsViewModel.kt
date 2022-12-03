package com.github.clockworkclyde.sweepyhelper.ui.rooms

import com.github.clockworkclyde.sweepyhelper.domain.usecases.rooms.LoadRoomsUseCase
import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.Room
import com.github.clockworkclyde.sweepyhelper.ui.base.BaseViewModel
import com.github.clockworkclyde.sweepyhelper.ui.base.BaseViewState
import kotlinx.coroutines.flow.Flow

class RoomsViewModel(private val loadRoomsUseCase: LoadRoomsUseCase) :
    BaseViewModel<BaseViewState<List<Room>>, RoomsViewEvent, RoomsViewEffect>() {

    override fun setInitialState(): BaseViewState<List<Room>> {
        return BaseViewState.Empty
    }

    override fun handleEvents(event: RoomsViewEvent) {
        when (event) {
            is RoomsViewEvent.LoadRooms -> {}
        }
    }

    private fun loadRooms(): Flow<List<Room>> {
        TODO()
        //        return loadRoomsUseCase().onEach {
//            setState {
//
//            }
//        }.launchIn(viewModelScope)
    }
}