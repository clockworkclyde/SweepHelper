package com.github.clockworkclyde.sweepyhelper.domain.usecases.rooms

import com.github.clockworkclyde.sweepyhelper.domain.repository.RoomsRepository
import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.Room

class LoadRoomByIdUseCase(private val repository: RoomsRepository) {

    suspend operator fun invoke(id: Long): Room {
        return repository.loadRoom(id)
    }
}