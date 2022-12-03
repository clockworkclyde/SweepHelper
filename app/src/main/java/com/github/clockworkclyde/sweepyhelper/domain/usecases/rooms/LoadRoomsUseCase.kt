package com.github.clockworkclyde.sweepyhelper.domain.usecases.rooms

import com.github.clockworkclyde.sweepyhelper.domain.repository.RoomsRepository
import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.Room
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LoadRoomsUseCase constructor(
    private val repository: RoomsRepository,
    private val getConditionUseCase: GetConditionForRoomUseCase
) {

    operator fun invoke(): Flow<List<Room>> {
        return repository.loadRooms().map { list ->
            list.map { room ->
                getConditionUseCase(room.id).let {
                    room.copy(condition = it)
                }
            }
        }
    }
}