package com.github.clockworkclyde.sweepyhelper.domain.usecases.compose

import com.github.clockworkclyde.sweepyhelper.domain.repository.RoomsRepository
import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.Room

class CreateEmptyRoomUseCase constructor(private val repository: RoomsRepository) {

    suspend operator fun invoke(room: Room) {
        repository.createRoom(room)
    }
}