package com.github.clockworkclyde.sweepyhelper.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.github.clockworkclyde.sweepyhelper.models.ui.base.AppBarState
import com.github.clockworkclyde.sweepyhelper.ui.compose.ComposeRoomScreen
import com.github.clockworkclyde.sweepyhelper.ui.compose.ComposeTaskScreen
import com.github.clockworkclyde.sweepyhelper.ui.compose.ComposeTaskViewModel
import com.github.clockworkclyde.sweepyhelper.ui.compose.ComposeViewTaskSuggestions
import com.github.clockworkclyde.sweepyhelper.ui.navigation.*
import com.github.clockworkclyde.sweepyhelper.ui.rooms.RoomsScreen
import com.github.clockworkclyde.sweepyhelper.ui.rooms.RoomsViewModel
import com.github.clockworkclyde.sweepyhelper.ui.tasks.TasksListScreen
import com.github.clockworkclyde.sweepyhelper.ui.tasks.TasksViewModel
import com.github.clockworkclyde.sweepyhelper.ui.theme.SweepyHelperTheme
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val appNavigationController = get<AppNavigationController>()

            LaunchedEffect(key1 = appNavigationController.destinations) {
                appNavigationController.destinations.collect { destination ->
                    when (destination) {
                        InitialDestination -> {}
                        NavigateUpDestination -> navController.navigateUp()
                        else -> navController.navigate(destination.route)
                    }
                }
            }

            SweepyHelperTheme(darkTheme = true) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var title by remember { mutableStateOf("") }
                    Scaffold(topBar = {
                        TopAppBar {
                            Text(text = title)
                        }
                    }) {
                        AppNavHost(
                            navController = navController,
                            appBarStateChanged = { title = it.title })
                    }
                }
            }
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController, appBarStateChanged: (AppBarState) -> Unit) {
    NavHost(navController = navController, startDestination = MainScreenDirections.Root.route) {
        roomsWithTasksGraph(appBarStateChanged = appBarStateChanged)
        composeGraph(appBarStateChanged = appBarStateChanged)
    }
}

private fun NavGraphBuilder.composeGraph(
    appBarStateChanged: (AppBarState) -> Unit
) {
    navigation(
        startDestination = ComposeDirections.ComposeRoom.route,
        route = ComposeDirections.Root.route
    ) {

        composable(ComposeDirections.ComposeRoom.route) {
            ComposeRoomScreen(
                viewModel = getViewModel(),
                onComposing = appBarStateChanged
            )
        }
        composable(ComposeDirections.TasksSuggestions.route) {
            ComposeViewTaskSuggestions(
                viewModel = getViewModel<ComposeTaskViewModel>(),
                appBarState = appBarStateChanged,
                roomId = it.arguments?.getLong(KEY_ROOM_ID)
                    .let { id -> checkNotNull(id) { "Room id is null" } }
            )
        }
        composable(ComposeDirections.ComposeTask.route) {
            val viewModel = getViewModel<ComposeTaskViewModel>()
            ComposeTaskScreen(
                viewModel = viewModel,
                regularities = viewModel.taskRegularities,
                onComposing = appBarStateChanged,
                roomId = it.arguments?.getLong(KEY_ROOM_ID)
                    .let { id -> checkNotNull(id) { "Room id is null" } }
            )
        }
    }
}

private fun NavGraphBuilder.roomsWithTasksGraph(
    appBarStateChanged: (AppBarState) -> Unit
) {
    navigation(
        startDestination = MainScreenDirections.Rooms.route,
        route = MainScreenDirections.Root.route,
        arguments = MainScreenDirections.Root.args
    ) {
        composable(MainScreenDirections.Rooms.route) {
            RoomsScreen(
                viewModel = getViewModel<RoomsViewModel>(),
                onComposing = appBarStateChanged
            )
        }
        composable(
            route = MainScreenDirections.Tasks.route,
            arguments = MainScreenDirections.Tasks.args
        ) {
            TasksListScreen(
                viewModel = getViewModel<TasksViewModel>(),
                roomId = it.arguments?.getLong(KEY_ROOM_ID)
                    .let { id -> checkNotNull(id) { "Room id is null" } }
            )
        }
        composable(
            route = MainScreenDirections.TaskDetails.route,
            arguments = MainScreenDirections.TaskDetails.args
        ) {
//            TaskDetailsScreen(
//                roomId = it.arguments?.getLong(MainScreenDirections.taskDetails.room),
//                taskId = it.arguments?.getLong(MainScreenDirections.taskId)
//            ) {
//
//            }
        }
    }
}