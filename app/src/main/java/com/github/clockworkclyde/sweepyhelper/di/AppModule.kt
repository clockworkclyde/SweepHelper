package com.github.clockworkclyde.sweepyhelper.di

import com.github.clockworkclyde.sweepyhelper.ui.navigation.DestinationManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule = module {
    val appCoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    provideDestinationManager(appCoroutineScope)
}

private fun Module.provideDestinationManager(scope: CoroutineScope) =
    single { DestinationManager(scope) }