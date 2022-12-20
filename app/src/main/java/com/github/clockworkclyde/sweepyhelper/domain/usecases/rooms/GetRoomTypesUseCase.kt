package com.github.clockworkclyde.sweepyhelper.domain.usecases.rooms

import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.RoomType

class GetRoomTypesUseCase {

    operator fun invoke(): List<RoomType> {
        return RoomType.values().toList()
    }
}