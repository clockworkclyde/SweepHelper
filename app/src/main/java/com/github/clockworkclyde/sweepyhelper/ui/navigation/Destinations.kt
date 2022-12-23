package com.github.clockworkclyde.sweepyhelper.ui.navigation

import com.github.clockworkclyde.sweepyhelper.models.ui.navigation.DestinationParams
import com.github.clockworkclyde.sweepyhelper.ui.navigation.ParamKey.ROOM_ID_KEY
import com.github.clockworkclyde.sweepyhelper.ui.navigation.ParamKey.TASK_ID_KEY

object NavigateUp : NavDestination(NAVIGATE_UP_ROUTE) {
    override val params = DestinationParams(route = route)
}

object RoomsFlow : NavDestination(ROOMS_FLOW_ROUTE) {
    override val params = DestinationParams(route = route)

    object Rooms : NavDestination(ROOMS_ROUTE) {
        override val params = DestinationParams(route = route)
    }

    data class RoomTasks(val roomId: Long) : NavDestination(ROOM_TASKS_ROUTE) {
        override val params = DestinationParams(route = route, param = ROOM_ID_KEY to roomId)
    }

    data class TaskDetails(val taskId: Long) : NavDestination(TASK_DETAILS_ROUTE) {
        override val params = DestinationParams(route = route, param = TASK_ID_KEY to taskId)
    }
}

object ComposeRoom : NavDestination(COMPOSE_ROOM_ROUTE) {
    override val params = DestinationParams(route = route)
}

data class ComposeTask(val roomId: Long) : NavDestination(COMPOSE_TASK_ROUTE) {
    override val params = DestinationParams(route = route, param = ROOM_ID_KEY to roomId)
}

data class ComposeTaskSuggestions(val roomId: Long) :
    NavDestination(COMPOSE_TASK_SUGGESTIONS_ROUTE) {
    override val params = DestinationParams(route = route, param = ROOM_ID_KEY to roomId)
}