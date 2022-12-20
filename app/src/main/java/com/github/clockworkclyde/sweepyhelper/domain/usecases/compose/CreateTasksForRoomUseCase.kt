package com.github.clockworkclyde.sweepyhelper.domain.usecases.compose

import com.github.clockworkclyde.sweepyhelper.domain.repository.TasksRepository
import com.github.clockworkclyde.sweepyhelper.models.ui.tasks.Task

class CreateTasksForRoomUseCase(private val repository: TasksRepository) {

    suspend operator fun invoke(tasks: List<Task>) {
        repository.createNewTasks(tasks)
    }
}