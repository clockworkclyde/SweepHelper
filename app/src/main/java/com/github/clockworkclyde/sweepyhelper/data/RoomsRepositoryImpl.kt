package com.github.clockworkclyde.sweepyhelper.data

import com.github.clockworkclyde.sweepyhelper.data.datasources.RoomsLocalDataSource
import com.github.clockworkclyde.sweepyhelper.domain.repository.RoomsRepository
import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.Room
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomsRepositoryImpl(private val dataSource: RoomsLocalDataSource) : RoomsRepository {

    override fun loadRooms(): Flow<List<Room>> {
        return dataSource.loadRooms().map { list ->
            list.map { it.convertTo() }.sortedBy { it.title.first() }
        }
    }

    override suspend fun loadRoom(id: Long): Room {
        return dataSource.loadRoom(id).convertTo()
    }

    override suspend fun createRoom(room: Room) {
        room.convertTo().let { dataSource.createNewRoom(it) }
    }

    override suspend fun removeRoom(id: Long) {
        dataSource.removeRoom(id)
    }
}