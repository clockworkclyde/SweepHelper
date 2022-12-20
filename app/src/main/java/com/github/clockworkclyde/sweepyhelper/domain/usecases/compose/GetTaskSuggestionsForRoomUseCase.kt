package com.github.clockworkclyde.sweepyhelper.domain.usecases.compose

import com.github.clockworkclyde.sweepyhelper.models.ui.tasks.Regularity
import com.github.clockworkclyde.sweepyhelper.models.ui.tasks.Task
import java.time.LocalDate

class GetTaskSuggestionsForRoomUseCase {

    operator fun invoke(id: Long): List<Task> {
        // todo
        return IntRange(1, 10).map {
            Task.create(
                title = "Task title #$it",
                owner = id,
                regularity = Regularity.Days,
                regularityQuantity = 3,
                startDate = LocalDate.now()
            )
        }
    }
}