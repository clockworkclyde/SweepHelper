package com.github.clockworkclyde.sweepyhelper.providers.datasources

import com.github.clockworkclyde.sweepyhelper.data.datasources.RoomsLocalDataSource
import com.github.clockworkclyde.sweepyhelper.models.local.rooms.RoomEntity
import com.github.clockworkclyde.sweepyhelper.providers.database.SweepyDao
import kotlinx.coroutines.flow.Flow

class RoomsLocalDataSourceImpl(private val dao: SweepyDao) : RoomsLocalDataSource {

    override fun loadRooms(): Flow<List<RoomEntity>> {
        return dao.getAllRooms()
    }

    override suspend fun createNewRoom(room: RoomEntity) {
        dao.createNewRoom(room)
    }

    override suspend fun removeRoom(id: Long) {
        dao.removeRoom(id)
    }
}