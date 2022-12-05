package com.github.clockworkclyde.sweepyhelper.domain.usecases.rooms

import com.github.clockworkclyde.sweepyhelper.domain.repository.TasksRepository
import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.Condition

class GetConditionForRoomUseCase(private val tasksRepository: TasksRepository) {

    suspend operator fun invoke(id: Long): Condition {
        return tasksRepository.getTasksByOwnerId(id)
            .takeIf { it.isNotEmpty() }
            ?.map { it.condition }
            ?.let {
                val count = it.count()
                calculateRoomCondition(it.sumOf { cond -> cond.value }, count)
            } ?: Condition.Empty
    }

    private fun calculateRoomCondition(sum: Int, count: Int): Condition {
        return Condition.Great.value.let { max ->
            val totalValue = count * max
            max * sum / totalValue
        }.let {
            getConditions().find { it.value == it.value }
                ?: throw NullPointerException("Cannot calculate result with this params")
        }
    }

    private fun getConditions(): Array<Condition> {
        return Condition.values()
    }
}