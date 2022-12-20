package com.github.clockworkclyde.sweepyhelper.di

import com.github.clockworkclyde.sweepyhelper.domain.usecases.compose.CreateEmptyRoomUseCase
import com.github.clockworkclyde.sweepyhelper.domain.usecases.compose.CreateTasksForRoomUseCase
import com.github.clockworkclyde.sweepyhelper.domain.usecases.compose.GetTaskRegularitiesUseCase
import com.github.clockworkclyde.sweepyhelper.domain.usecases.compose.GetTaskSuggestionsForRoomUseCase
import com.github.clockworkclyde.sweepyhelper.domain.usecases.rooms.GetConditionForRoomUseCase
import com.github.clockworkclyde.sweepyhelper.domain.usecases.rooms.GetRoomTypesUseCase
import com.github.clockworkclyde.sweepyhelper.domain.usecases.rooms.LoadRoomsUseCase
import com.github.clockworkclyde.sweepyhelper.ui.compose.ComposeViewModel
import com.github.clockworkclyde.sweepyhelper.ui.rooms.RoomsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { RoomsViewModel(get(), get()) }
    viewModel { ComposeViewModel(get(), get(), get(), get(), get(), get()) }

    // UseCases
    single { LoadRoomsUseCase(get(), get()) }
    single { GetConditionForRoomUseCase(get()) }
    single { CreateEmptyRoomUseCase(get()) }
    single { GetRoomTypesUseCase() }
    single { GetTaskRegularitiesUseCase() }
    single { CreateTasksForRoomUseCase(get()) }
    single { GetTaskSuggestionsForRoomUseCase() }
}