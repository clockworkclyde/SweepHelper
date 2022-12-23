package com.github.clockworkclyde.sweepyhelper.domain.usecases.tasks

import com.github.clockworkclyde.sweepyhelper.domain.repository.TasksRepository
import com.github.clockworkclyde.sweepyhelper.models.ui.tasks.Task

class LoadTasksByRoomIdUseCase(private val tasksRepository: TasksRepository) {

    suspend operator fun invoke(id: Long): List<Task> {
        return tasksRepository.getTasksByOwnerId(id)
    }
}