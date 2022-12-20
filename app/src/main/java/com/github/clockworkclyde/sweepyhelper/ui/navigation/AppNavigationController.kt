package com.github.clockworkclyde.sweepyhelper.ui.navigation

import com.github.clockworkclyde.sweepyhelper.utils.logg
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class AppNavigationController(private val applicationScope: CoroutineScope) {

    private val _destinations = MutableSharedFlow<NavDestination>()
    val destinations = _destinations.asSharedFlow()

    fun setNewDestination(direction: NavDestination) {
        applicationScope.launch { _destinations.emit(direction) }
    }

    init {
        _destinations.onEach {
            logg { it.toString() }
        }.launchIn(applicationScope)
    }
}