package com.github.clockworkclyde.sweepyhelper.di

import com.github.clockworkclyde.sweepyhelper.ui.rooms.RoomsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::RoomsViewModel)
}