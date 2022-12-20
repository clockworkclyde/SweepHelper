package com.github.clockworkclyde.sweepyhelper.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed interface NavDestination {
    val route: String
    val args: List<NamedNavArgument>
}

object InitialDestination : NavDestination {
    override val route: String = ""
    override val args: List<NamedNavArgument> = emptyList()
}

object NavigateUpDestination : NavDestination {
    override val route: String = ""
    override val args: List<NamedNavArgument> = emptyList()
}

object MainScreenDirections {

    const val KEY_ROOM_ID = "roomId"
    const val KEY_TASK_ID = "taskId"

    object Root : NavDestination {
        override val route: String = "home"
        override val args: List<NamedNavArgument> = emptyList()
    }

    object Rooms : NavDestination {
        override val route: String = "rooms"
        override val args: List<NamedNavArgument> = emptyList()
    }

    object Tasks : NavDestination {
        override val route: String = "$KEY_ROOM_ID/tasks"
        override val args: List<NamedNavArgument> = listOf(navArgument(KEY_ROOM_ID) {
            type = NavType.LongType
        })
    }

    object TaskDetails : NavDestination {

        override val route: String = "$KEY_ROOM_ID/tasks/$KEY_TASK_ID"
        override val args: List<NamedNavArgument> = listOf(
            navArgument(KEY_ROOM_ID) { type = NavType.LongType },
            navArgument(KEY_TASK_ID) { type = NavType.LongType })
    }
}

object ComposeDirections {

    object Root : NavDestination {
        override val route: String = "compose"
        override val args: List<NamedNavArgument> = emptyList()
    }

    object ComposeRoom : NavDestination {
        override val route: String = "compose_room"
        override val args: List<NamedNavArgument> = emptyList()
    }

    object TasksSuggestions : NavDestination {
        override val route: String = "tasks_suggestions"
        override val args: List<NamedNavArgument> = emptyList()
    }

    object ComposeTask : NavDestination {
        override val route: String = "compose_task"
        override val args: List<NamedNavArgument> = emptyList()
    }
}