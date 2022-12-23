package com.github.clockworkclyde.sweepyhelper.ui.navigation

import com.github.clockworkclyde.sweepyhelper.utils.logg
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DestinationManager(
    private val applicationScope: CoroutineScope
) {
    //private val _map: MutableMap<String, Any?> = mutableMapOf()

    private val _destinations = MutableSharedFlow<NavDestination>()
    val destinations = _destinations.asSharedFlow()

    fun setDestination(destination: NavDestination) {
        applicationScope.launch {
            _destinations.emit(destination)
        }
    }

    init {
        _destinations.onEach {
            logg { it.toString() }
        }.launchIn(applicationScope)
    }
}