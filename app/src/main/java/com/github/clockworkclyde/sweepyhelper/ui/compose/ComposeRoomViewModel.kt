package com.github.clockworkclyde.sweepyhelper.ui.compose

import androidx.lifecycle.viewModelScope
import com.github.clockworkclyde.sweepyhelper.domain.usecases.compose.CreateEmptyRoomUseCase
import com.github.clockworkclyde.sweepyhelper.domain.usecases.rooms.GetRoomTypesUseCase
import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.Room
import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.RoomType
import com.github.clockworkclyde.sweepyhelper.ui.base.BaseViewModel
import com.github.clockworkclyde.sweepyhelper.ui.compose.contract.*
import com.github.clockworkclyde.sweepyhelper.ui.navigation.DestinationManager
import com.github.clockworkclyde.sweepyhelper.ui.navigation.NavigateUp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ComposeRoomViewModel(
    private val getRoomTypes: GetRoomTypesUseCase,
    private val createEmptyRoom: CreateEmptyRoomUseCase,
    private val destinationManager: DestinationManager
) :
    BaseViewModel<ComposeRoomViewState, ComposeRoomViewEvent, ComposeRoomViewEffect>() {

    val roomTypes by lazy { getRoomTypes() }

    override fun setInitialState(): ComposeRoomViewState {
        return ComposeRoomViewState(
            title = "",
            type = RoomType.NotSelected,
            inProgress = false,
            isError = false,
            isDataSaved = false
        )
    }

    override fun handleEvents(event: ComposeRoomViewEvent) {
        when (event) {
            is ComposeRoomViewEvent.CreateRoomButtonClicked -> validateRoomTitle()
            is ComposeRoomViewEvent.RoomTitleChanged -> setState { copyToUpdateRoomTitle(event.title) }
            is ComposeRoomViewEvent.RoomTypeChanged -> setState { copyToUpdateRoomType(event.type) }
        }
    }

    private fun validateRoomTitle() {
        val state = currentState()
        val title = state.title
        val type = state.type
        if (title.trim().isNotEmpty()) {
            createNewRoom(title, type)
        } else setEffect { ComposeRoomViewEffect.TitleValidationFailure }
    }

    private fun createNewRoom(title: String, type: RoomType) {
        val room = Room.create(title, type)
        setState { copyToProgress() }
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                createEmptyRoom(room)
            }
            setState { copyToSaveLocallySuccess() }
            destinationManager.setDestination(NavigateUp)
        }
    }
}