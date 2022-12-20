package com.github.clockworkclyde.sweepyhelper.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

interface NavDirection {
    val route: String
    val args: List<NamedNavArgument>
}

object MainScreenDirections {

    const val KEY_ROOM_ID = "roomId"
    const val KEY_TASK_ID = "taskId"

    val root = object : NavDirection {
        override val route: String = "home"
        override val args: List<NamedNavArgument> = emptyList()
    }

    val rooms = object : NavDirection {
        override val route: String = "rooms"
        override val args: List<NamedNavArgument> = emptyList()
    }

    val tasks = object : NavDirection {
        private val room = "roomId"

        override val route: String = "$room/tasks"
        override val args: List<NamedNavArgument> = listOf(navArgument(room) {
            type = NavType.LongType
        })
    }

    val taskDetails = object : NavDirection {

        override val route: String = "$KEY_ROOM_ID/tasks/$KEY_TASK_ID"
        override val args: List<NamedNavArgument> = listOf(
            navArgument(KEY_ROOM_ID) { type = NavType.LongType },
            navArgument(KEY_TASK_ID) { type = NavType.LongType })
    }
}

object ComposeDirections {

    val root = object : NavDirection {
        override val route: String = "compose"
        override val args: List<NamedNavArgument> = emptyList()
    }
    val composeRoom = object : NavDirection {
        override val route: String = "compose_room"
        override val args: List<NamedNavArgument> = emptyList()
    }
    val tasksSuggestions = object : NavDirection {
        override val route: String = "tasks_suggestions"
        override val args: List<NamedNavArgument> = emptyList()
    }
    val composeTask = object : NavDirection {
        override val route: String = "compose_task"
        override val args: List<NamedNavArgument> = emptyList()
    }
}