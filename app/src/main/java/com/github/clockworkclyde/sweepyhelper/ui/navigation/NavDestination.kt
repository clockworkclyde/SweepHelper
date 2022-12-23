package com.github.clockworkclyde.sweepyhelper.ui.navigation

import com.github.clockworkclyde.sweepyhelper.models.ui.navigation.DestinationParams

sealed class NavDestination(protected val route: String) {
    abstract val params: DestinationParams
}