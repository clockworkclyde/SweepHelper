package com.github.clockworkclyde.sweepyhelper.models.data.local.rooms

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.Room
import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.RoomType
import com.github.clockworkclyde.sweepyhelper.utils.IConvertableTo

@Entity
data class RoomEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val title: String,
    val type: RoomType
) : IConvertableTo<Room> {

    override fun convertTo(): Room {
        return Room(id = id, title = title, type = type)
    }
}