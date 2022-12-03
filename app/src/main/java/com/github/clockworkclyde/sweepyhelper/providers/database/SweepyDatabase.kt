package com.github.clockworkclyde.sweepyhelper.providers.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [], views = [], version = 1, exportSchema = false)
abstract class SweepyDatabase : RoomDatabase() {

    abstract fun getRoomsDao(): RoomsDao
    abstract fun getTasksDao(): TasksDao

}