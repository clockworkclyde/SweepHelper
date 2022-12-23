package com.github.clockworkclyde.sweepyhelper.models.ui.navigation

data class DestinationParams(
    val route: String,
    val param: Pair<String, Any>? = null
)