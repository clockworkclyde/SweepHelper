package com.github.clockworkclyde.sweepyhelper.domain.repository

import com.github.clockworkclyde.sweepyhelper.models.ui.tasks.Task
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
    fun getTasks(): Flow<List<Task>>
    suspend fun getTasksByOwnerId(id: Long): List<Task>
    suspend fun createNewTasks(tasks: List<Task>)
    suspend fun removeTask(id: Long)
}