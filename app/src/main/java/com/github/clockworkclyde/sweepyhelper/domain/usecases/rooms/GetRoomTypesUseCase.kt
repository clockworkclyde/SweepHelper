package com.github.clockworkclyde.sweepyhelper.domain.usecases.rooms

import com.github.clockworkclyde.sweepyhelper.domain.repository.RoomsRepository
import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.RoomType

class GetRoomTypesUseCase(private val repository: RoomsRepository) {

    operator fun invoke(): List<RoomType> {
        return repository.getRoomTypes()
    }
}