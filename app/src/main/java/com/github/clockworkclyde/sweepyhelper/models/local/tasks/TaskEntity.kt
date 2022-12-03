package com.github.clockworkclyde.sweepyhelper.models.local.tasks

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.clockworkclyde.sweepyhelper.models.ui.tasks.Regularity
import com.github.clockworkclyde.sweepyhelper.models.ui.tasks.Task
import com.github.clockworkclyde.sweepyhelper.utils.IConvertableTo
import org.joda.time.DateTime

@Entity
data class TaskEntity(
    @PrimaryKey val id: Long,
    val title: String,
    val owner: Long,
    val regularity: Regularity,
    val isOnRepeatNow: Boolean,
    val startDate: DateTime,
    val lastCleanedUpAt: DateTime
) : IConvertableTo<Task> {
    override fun convertTo(): Task {
        return Task(
            id = id,
            title = title,
            owner = owner,
            regularity = regularity,
            isOnRepeatNow = isOnRepeatNow,
            startDate = startDate,
            lastCleanedUpAt = lastCleanedUpAt
        )
    }
}