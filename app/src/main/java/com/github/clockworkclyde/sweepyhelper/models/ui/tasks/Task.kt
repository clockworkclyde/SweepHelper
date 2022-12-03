package com.github.clockworkclyde.sweepyhelper.models.ui.tasks

import com.github.clockworkclyde.sweepyhelper.models.local.tasks.TaskEntity
import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.Condition
import com.github.clockworkclyde.sweepyhelper.utils.IConvertableTo
import org.joda.time.DateTime

data class Task(
    val id: Long,
    val title: String,
    val owner: Long,
    val startDate: DateTime,
    val lastCleanedUpAt: DateTime,
    val condition: Condition = Condition.Empty,
    val regularity: Regularity,
    val isOnRepeatNow: Boolean = false
) : IConvertableTo<TaskEntity> {

    override fun convertTo(): TaskEntity {
        return TaskEntity(
            id = id,
            title = title,
            owner = owner,
            startDate = startDate,
            lastCleanedUpAt = lastCleanedUpAt,
            regularity = regularity,
            isOnRepeatNow = isOnRepeatNow
        )
    }
}

enum class Regularity(val amount: Int = 0) {
    Hours, Days, Weeks, Months, Years
}