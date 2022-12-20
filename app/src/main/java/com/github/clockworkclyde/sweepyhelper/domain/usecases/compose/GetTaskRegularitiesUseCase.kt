package com.github.clockworkclyde.sweepyhelper.domain.usecases.compose

import com.github.clockworkclyde.sweepyhelper.models.ui.tasks.Regularity

class GetTaskRegularitiesUseCase {

    operator fun invoke(): List<Regularity> {
        return Regularity.values().toList()
    }
}