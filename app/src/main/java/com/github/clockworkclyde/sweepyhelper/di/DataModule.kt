package com.github.clockworkclyde.sweepyhelper.di

import com.github.clockworkclyde.sweepyhelper.data.RoomsRepositoryImpl
import com.github.clockworkclyde.sweepyhelper.data.TasksRepositoryImpl
import com.github.clockworkclyde.sweepyhelper.data.datasources.RoomsLocalDataSource
import com.github.clockworkclyde.sweepyhelper.data.datasources.TasksLocalDataSource
import com.github.clockworkclyde.sweepyhelper.domain.repository.RoomsRepository
import com.github.clockworkclyde.sweepyhelper.domain.repository.TasksRepository
import org.koin.dsl.module

val dataModule = module {
    single<RoomsRepository> { RoomsRepositoryImpl(get()) }
    single<TasksRepository> { TasksRepositoryImpl(get()) }

    factory { get<TasksLocalDataSource>() }
    factory { get<RoomsLocalDataSource>() }
}

