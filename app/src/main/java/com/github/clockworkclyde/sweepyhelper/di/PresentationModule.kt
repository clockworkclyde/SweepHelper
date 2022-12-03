package com.github.clockworkclyde.sweepyhelper.di

import com.github.clockworkclyde.sweepyhelper.domain.usecases.rooms.GetConditionForRoomUseCase
import com.github.clockworkclyde.sweepyhelper.domain.usecases.rooms.LoadRoomsUseCase
import com.github.clockworkclyde.sweepyhelper.ui.rooms.RoomsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { RoomsViewModel(get()) }
    single { LoadRoomsUseCase(get(), get()) }
    single { GetConditionForRoomUseCase(get()) }
}