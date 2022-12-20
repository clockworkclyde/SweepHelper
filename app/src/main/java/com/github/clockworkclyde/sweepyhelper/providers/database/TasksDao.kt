package com.github.clockworkclyde.sweepyhelper.providers.database

import androidx.room.*
import com.github.clockworkclyde.sweepyhelper.models.local.tasks.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

    @Query("SELECT * FROM TaskEntity")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM TaskEntity WHERE owner = :id")
    suspend fun getTasksByOwnerId(id: Long): List<TaskEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createNewTasks(task: List<TaskEntity>)

    @Update
    suspend fun updateTaskData(task: TaskEntity)

    @Query("DELETE FROM TaskEntity WHERE id = :id")
    suspend fun removeTask(id: Long)
}