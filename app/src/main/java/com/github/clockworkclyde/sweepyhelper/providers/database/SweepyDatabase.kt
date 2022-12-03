package com.github.clockworkclyde.sweepyhelper.providers.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.clockworkclyde.sweepyhelper.models.local.rooms.RoomEntity
import com.github.clockworkclyde.sweepyhelper.models.local.tasks.TaskEntity

@Database(
    entities = [RoomEntity::class, TaskEntity::class],
    views = [],
    version = 1,
    exportSchema = false
)
abstract class SweepyDatabase : RoomDatabase() {

    abstract fun getRoomsDao(): RoomsDao
    abstract fun getTasksDao(): TasksDao

}