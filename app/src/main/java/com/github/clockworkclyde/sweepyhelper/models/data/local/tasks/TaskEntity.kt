package com.github.clockworkclyde.sweepyhelper.models.data.local.tasks

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.clockworkclyde.sweepyhelper.models.ui.tasks.Regularity
import com.github.clockworkclyde.sweepyhelper.models.ui.tasks.Task
import com.github.clockworkclyde.sweepyhelper.utils.DateTimeConverter
import com.github.clockworkclyde.sweepyhelper.utils.IConvertableTo

@Entity
data class TaskEntity(
    @PrimaryKey val id: Long,
    val title: String,
    val owner: Long,
    val regularityQuantity: Int,
    val regularity: Regularity,
    val isOnRepeatNow: Boolean,
    val startDate: String,
    val lastCleanedUpAt: String,
    val creationTimeMillis: Long
) : IConvertableTo<Task> {

    override fun convertTo(): Task {
        return Task(
            id = id,
            title = title,
            owner = owner,
            regularity = regularity,
            isOnRepeatNow = isOnRepeatNow,
            startDate = DateTimeConverter.stringToDate(startDate),
            lastCleanedUpAt = DateTimeConverter.stringToDate(lastCleanedUpAt),
            creationTimeMillis = creationTimeMillis,
            regularityQuantity = regularityQuantity
        )
    }
}