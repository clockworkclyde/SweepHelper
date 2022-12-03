package com.github.clockworkclyde.sweepyhelper.providers.datasources

import com.github.clockworkclyde.sweepyhelper.data.datasources.TasksLocalDataSource
import com.github.clockworkclyde.sweepyhelper.models.local.tasks.TaskEntity
import com.github.clockworkclyde.sweepyhelper.providers.database.TasksDao
import kotlinx.coroutines.flow.Flow

class TasksLocalDataSourceImpl(private val dao: TasksDao) : TasksLocalDataSource {

    override fun getAllTasks(): Flow<List<TaskEntity>> {
        return dao.getAllTasks()
    }

    override suspend fun getTasksByOwnerId(id: Long): List<TaskEntity> {
        return dao.getTasksByOwnerId(id)
    }

    override suspend fun createNewTask(task: TaskEntity) {
        dao.createNewTask(task)
    }

    override suspend fun removeTask(id: Long) {
        dao.removeTask(id)
    }
}