package com.github.clockworkclyde.sweepyhelper.models.ui.tasks

import com.github.clockworkclyde.sweepyhelper.R
import com.github.clockworkclyde.sweepyhelper.models.local.tasks.TaskEntity
import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.Condition
import com.github.clockworkclyde.sweepyhelper.utils.DateTimeConverter
import com.github.clockworkclyde.sweepyhelper.utils.IConvertableTo
import java.time.LocalDate

data class Task(
    val id: Long = 0L,
    val title: String,
    val owner: Long,
    val startDate: LocalDate,
    val lastCleanedUpAt: LocalDate,
    val creationTimeMillis: Long,
    val condition: Condition = Condition.Empty,
    val regularityQuantity: Int,
    val regularity: Regularity,
    val isOnRepeatNow: Boolean = false
) : IConvertableTo<TaskEntity> {

    override fun convertTo(): TaskEntity {
        return TaskEntity(
            id = id,
            title = title,
            owner = owner,
            startDate = DateTimeConverter.dateToString(startDate),
            lastCleanedUpAt = DateTimeConverter.dateToString(lastCleanedUpAt),
            regularity = regularity,
            isOnRepeatNow = isOnRepeatNow,
            creationTimeMillis = creationTimeMillis,
            regularityQuantity = regularityQuantity
        )
    }

    companion object {
        fun create(
            title: String,
            owner: Long,
            startDate: LocalDate,
            regularityQuantity: Int,
            regularity: Regularity
        ): Task {
            val now = System.currentTimeMillis()
            return Task(
                id = title.hashCode().toLong() + now,
                title = title,
                owner = owner,
                startDate = startDate,
                lastCleanedUpAt = LocalDate.now(),
                regularity = regularity,
                creationTimeMillis = now,
                regularityQuantity = regularityQuantity
            )
        }
    }
}

enum class Regularity(val titleId: Int) {
    Hours(R.string.regularity_hours), Days(R.string.days), Weeks(R.string.weeks), Months(R.string.months)
}