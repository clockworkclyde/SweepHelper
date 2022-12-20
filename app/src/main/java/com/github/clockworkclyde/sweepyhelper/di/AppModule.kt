package com.github.clockworkclyde.sweepyhelper.di

import com.github.clockworkclyde.sweepyhelper.ui.navigation.AppNavigationController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule = module {
    val appCoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    provideNavigationController(appCoroutineScope)
}

private fun Module.provideNavigationController(scope: CoroutineScope) =
    single { AppNavigationController(scope) }