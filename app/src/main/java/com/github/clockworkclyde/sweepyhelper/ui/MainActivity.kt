package com.github.clockworkclyde.sweepyhelper.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.github.clockworkclyde.sweepyhelper.models.ui.base.AppBarState
import com.github.clockworkclyde.sweepyhelper.ui.compose.*
import com.github.clockworkclyde.sweepyhelper.ui.navigation.ComposeDirections
import com.github.clockworkclyde.sweepyhelper.ui.navigation.MainScreenDirections
import com.github.clockworkclyde.sweepyhelper.ui.rooms.RoomsScreen
import com.github.clockworkclyde.sweepyhelper.ui.rooms.RoomsViewModel
import com.github.clockworkclyde.sweepyhelper.ui.tasks.TasksListScreen
import com.github.clockworkclyde.sweepyhelper.ui.tasks.TasksViewModel
import com.github.clockworkclyde.sweepyhelper.ui.theme.SweepyHelperTheme
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SweepyHelperTheme(darkTheme = true) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var title by remember { mutableStateOf("") }
                    val navController = rememberNavController()
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
    NavHost(navController = navController, startDestination = MainScreenDirections.root.route) {
        roomsWithTasksGraph(navController = navController, AppBarStateChanged = appBarStateChanged)
        composeGraph(navController = navController, appBarState = appBarStateChanged)
    }
}

private fun NavGraphBuilder.composeGraph(
    navController: NavController,
    appBarState: (AppBarState) -> Unit
) {
    navigation(
        startDestination = ComposeDirections.composeRoom.route,
        route = ComposeDirections.root.route
    ) {

        composable(ComposeDirections.composeRoom.route) {
            ComposeRoomScreen(
                navController = navController,
                viewModel = getViewModel<ComposeViewModel>(),
                onComposing = appBarState
            )
        }
        composable(ComposeDirections.tasksSuggestions.route) {
            val viewModel = getViewModel<ComposeViewModel>()
            ComposeViewTaskSuggestions(
                viewModel = viewModel,
                onSaveTasksClicked = { viewModel.setEvent(ComposeViewEvent.SubmitTasksSuggestions(it)) },
                appBarState = appBarState
            )
        }
        composable(ComposeDirections.composeTask.route) {
            val viewModel = getViewModel<ComposeViewModel>()
            ComposeTaskScreen(
                viewModel = viewModel,
                regularities = viewModel.taskRegularities,
                onTaskCreated = { },
                onComposing = appBarState
            )
        }
    }
}

private fun NavGraphBuilder.roomsWithTasksGraph(
    navController: NavHostController,
    AppBarStateChanged: (AppBarState) -> Unit
) {
    navigation(
        startDestination = MainScreenDirections.rooms.route,
        route = MainScreenDirections.root.route,
        arguments = MainScreenDirections.root.args
    ) {
        composable(MainScreenDirections.rooms.route) {
            RoomsScreen(
                navController = navController,
                viewModel = getViewModel<RoomsViewModel>(),
                onComposing = AppBarStateChanged
            )
        }
        composable(
            route = MainScreenDirections.tasks.route,
            arguments = MainScreenDirections.tasks.args
        ) {
            TasksListScreen(
                navController = navController,
                viewModel = getViewModel<TasksViewModel>(),
                roomId = it.arguments?.getLong(MainScreenDirections.KEY_ROOM_ID)
            )
        }
        composable(
            route = MainScreenDirections.taskDetails.route,
            arguments = MainScreenDirections.taskDetails.args
        ) {
//            TaskDetailsScreen(
//                navController = navController,
//                roomId = it.arguments?.getLong(MainScreenDirections.taskDetails.room),
//                taskId = it.arguments?.getLong(MainScreenDirections.taskId)
//            ) {
//
//            }
        }
    }
}