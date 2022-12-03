package com.github.clockworkclyde.sweepyhelper.providers.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.clockworkclyde.sweepyhelper.models.local.rooms.RoomEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomsDao {

    @Query("SELECT * FROM RoomEntity")
    fun getAllRooms(): Flow<List<RoomEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createNewRoom(room: RoomEntity)

    @Query("DELETE FROM RoomEntity WHERE id = :id")
    suspend fun removeRoom(id: Long)
}