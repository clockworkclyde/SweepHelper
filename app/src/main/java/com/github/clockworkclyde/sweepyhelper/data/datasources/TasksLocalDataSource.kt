package com.github.clockworkclyde.sweepyhelper.data.datasources

import com.github.clockworkclyde.sweepyhelper.models.local.tasks.TaskEntity
import kotlinx.coroutines.flow.Flow

interface TasksLocalDataSource {
    fun getAllTasks(): Flow<List<TaskEntity>>
    suspend fun getTasksByOwnerId(id: Long): List<TaskEntity>
    suspend fun createNewTasks(task: List<TaskEntity>)
    suspend fun removeTask(id: Long)
}