package com.github.clockworkclyde.sweepyhelper.models.ui.rooms

import com.github.clockworkclyde.sweepyhelper.R
import com.github.clockworkclyde.sweepyhelper.models.local.rooms.RoomEntity
import com.github.clockworkclyde.sweepyhelper.utils.IConvertableTo

data class Room(
    val id: Long,
    val title: String,
    val type: RoomType,
    val condition: Condition = Condition.Empty
) : IConvertableTo<RoomEntity> {
    override fun convertTo(): RoomEntity {
        return RoomEntity(id = id, title = title, type = type)
    }
}

enum class Condition(val id: Int, val value: Int) {
    CriticalLow(R.string.condition_critical_low, 2),
    Low(R.string.condition_low, 4),
    Medium(R.string.condition_medium, 6),
    Fine(R.string.condition_fine, 8),
    Great(R.string.condition_great, 10),
    Empty(R.string.condition_empty, -1)
}

enum class RoomType {
    Kitchen, Hall, Bathroom, Bedroom, Wardrobe
}