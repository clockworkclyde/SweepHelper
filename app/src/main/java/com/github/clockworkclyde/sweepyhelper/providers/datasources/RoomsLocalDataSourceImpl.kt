package com.github.clockworkclyde.sweepyhelper.providers.datasources

import com.github.clockworkclyde.sweepyhelper.data.datasources.RoomsLocalDataSource
import com.github.clockworkclyde.sweepyhelper.models.data.local.rooms.RoomEntity
import com.github.clockworkclyde.sweepyhelper.providers.database.RoomsDao
import kotlinx.coroutines.flow.Flow

class RoomsLocalDataSourceImpl(private val dao: RoomsDao) : RoomsLocalDataSource {

    override fun loadRooms(): Flow<List<RoomEntity>> {
        return dao.getAllRooms()
    }

    override suspend fun loadRoom(id: Long): RoomEntity {
        return dao.getRoom(id)
    }

    override suspend fun createNewRoom(room: RoomEntity) {
        dao.createNewRoom(room)
    }

    override suspend fun removeRoom(id: Long) {
        dao.removeRoom(id)
    }
}