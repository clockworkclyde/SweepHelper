package com.github.clockworkclyde.sweepyhelper.di

import androidx.room.Room
import com.github.clockworkclyde.sweepyhelper.providers.database.SweepyDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(androidApplication(), SweepyDatabase::class.java, "SweepyDatabase")
            .build()
    }
    single {
        get<SweepyDatabase>().getDao()
    }
}