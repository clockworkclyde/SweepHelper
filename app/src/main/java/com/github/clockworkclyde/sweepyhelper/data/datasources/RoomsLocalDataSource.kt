package com.github.clockworkclyde.sweepyhelper.data.datasources

import com.github.clockworkclyde.sweepyhelper.models.data.local.rooms.RoomEntity
import kotlinx.coroutines.flow.Flow

interface RoomsLocalDataSource {
    fun loadRooms(): Flow<List<RoomEntity>>
    suspend fun loadRoom(id: Long): RoomEntity
    suspend fun createNewRoom(room: RoomEntity)
    suspend fun removeRoom(id: Long)
}