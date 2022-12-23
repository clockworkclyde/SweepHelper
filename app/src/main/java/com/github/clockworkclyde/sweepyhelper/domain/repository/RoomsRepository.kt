package com.github.clockworkclyde.sweepyhelper.domain.repository

import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.Room
import kotlinx.coroutines.flow.Flow

interface RoomsRepository {
    fun loadRooms(): Flow<List<Room>>
    suspend fun loadRoom(id: Long): Room
    suspend fun createRoom(room: Room)
    suspend fun removeRoom(id: Long)
}