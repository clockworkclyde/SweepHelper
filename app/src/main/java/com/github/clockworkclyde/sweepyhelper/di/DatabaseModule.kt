package com.github.clockworkclyde.sweepyhelper.di

import android.app.Application
import androidx.room.Room
import com.github.clockworkclyde.sweepyhelper.providers.database.SweepyDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    fun provideDatabase(app: Application): SweepyDatabase {
        return Room.databaseBuilder(app, SweepyDatabase::class.java, "SweepyDatabase")
            .fallbackToDestructiveMigration()
            .build()
    }

    single { provideDatabase(androidApplication()) }
    single { get<SweepyDatabase>().getRoomsDao() }
    single { get<SweepyDatabase>().getTasksDao() }
}