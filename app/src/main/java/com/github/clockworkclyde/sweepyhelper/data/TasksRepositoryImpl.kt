package com.github.clockworkclyde.sweepyhelper.data

import com.github.clockworkclyde.sweepyhelper.data.datasources.TasksLocalDataSource
import com.github.clockworkclyde.sweepyhelper.domain.repository.TasksRepository
import com.github.clockworkclyde.sweepyhelper.models.ui.tasks.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TasksRepositoryImpl(private val dataSource: TasksLocalDataSource) : TasksRepository {

    override fun getTasks(): Flow<List<Task>> {
        return dataSource.getAllTasks().map { list ->
            list.map { it.convertTo() }
                .sortedBy { it.title.first() }
        }
    }

    override suspend fun getTasksByOwnerId(id: Long): List<Task> {
        return dataSource.getTasksByOwnerId(id)
            .map { it.convertTo() }
            .sortedBy { it.title.first() }
    }

    override suspend fun createNewTasks(tasks: List<Task>) {
        tasks.map { it.convertTo() }.let {
            dataSource.createNewTasks(it)
        }
    }

    override suspend fun removeTask(id: Long) {
        dataSource.removeTask(id)
    }
}