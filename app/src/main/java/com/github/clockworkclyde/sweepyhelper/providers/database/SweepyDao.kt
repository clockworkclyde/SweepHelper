package com.github.clockworkclyde.sweepyhelper.providers.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.clockworkclyde.sweepyhelper.models.local.rooms.RoomEntity
import com.github.clockworkclyde.sweepyhelper.models.local.tasks.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SweepyDao {
    // separate to two dao
    // Rooms

    @Query("SELECT * FROM RoomEntity")
    fun getAllRooms(): Flow<List<RoomEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createNewRoom(room: RoomEntity)

    @Query("DELETE FROM RoomEntity WHERE id = :id")
    suspend fun removeRoom(id: Long)

    // Tasks

    @Query("SELECT * FROM TaskEntity")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM TaskEntity WHERE owner = :id")
    suspend fun getTasksByOwnerId(id: Long): List<TaskEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createNewTask(task: TaskEntity)

    @Query("DELETE FROM TaskEntity WHERE id = :id")
    suspend fun removeTask(id: Long)
}