package com.github.clockworkclyde.sweepyhelper.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.unit.dp
import androidx.navigation.*
import com.github.clockworkclyde.sweepyhelper.R
import com.github.clockworkclyde.sweepyhelper.models.ui.base.AppBarState
import com.github.clockworkclyde.sweepyhelper.ui.compose.ComposeRoomScreen
import com.github.clockworkclyde.sweepyhelper.ui.compose.ComposeTaskScreen
import com.github.clockworkclyde.sweepyhelper.ui.compose.ComposeTaskViewModel
import com.github.clockworkclyde.sweepyhelper.ui.compose.ComposeViewTaskSuggestions
import com.github.clockworkclyde.sweepyhelper.ui.navigation.*
import com.github.clockworkclyde.sweepyhelper.ui.navigation.NavDestination
import com.github.clockworkclyde.sweepyhelper.ui.rooms.RoomsScreen
import com.github.clockworkclyde.sweepyhelper.ui.rooms.RoomsViewModel
import com.github.clockworkclyde.sweepyhelper.ui.tasks.TasksListScreen
import com.github.clockworkclyde.sweepyhelper.ui.tasks.TasksViewModel
import com.github.clockworkclyde.sweepyhelper.ui.theme.SweepyHelperTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberAnimatedNavController()
            val destinationManager = get<DestinationManager>()

            LaunchedEffect(key1 = destinationManager.destinations) {
                destinationManager.destinations.collect { dest ->
                    handleNavigationActions(navHostController = navController, destination = dest)
                }
            }

            SweepyHelperTheme(darkTheme = true) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var title by remember { mutableStateOf("") }
                    Scaffold(topBar = {
                        TopAppBar(title = { Text(text = title) })
                    }) {
                        AppNavHost(navController = navController,
                            appBarStateChanged = { title = it.title })
                    }
                }
            }
        }
    }
}

fun handleNavigationActions(
    navHostController: NavHostController, destination: NavDestination
) {
    val route = destination.params.let {
        it.param?.let { (key, param) ->
            it.route.replace("{$key}", "$param", ignoreCase = true)
        } ?: it.route
    }
    when (destination) {
        is NavigateUp -> navHostController.navigateUp()
        else -> navHostController.navigate(route)
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavHost(navController: NavHostController, appBarStateChanged: (AppBarState) -> Unit) {

    val enterDuration = integerResource(R.integer.motion_middle_enter_duration)
    val exitDuration = integerResource(R.integer.motion_middle_exit_duration)

    AnimatedNavHost(
        modifier = Modifier.padding(all = 12.dp),
        navController = navController,
        startDestination = ROOMS_FLOW_ROUTE,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Up, animationSpec = tween(enterDuration)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Down, animationSpec = tween(exitDuration)
            )
        }) {
        roomsFlowScreens(appBarStateChanged = appBarStateChanged)
        composeScreens(appBarStateChanged = appBarStateChanged)
    }
}

@OptIn(ExperimentalAnimationApi::class)
private fun NavGraphBuilder.composeScreens(
    appBarStateChanged: (AppBarState) -> Unit
) {
    composable(COMPOSE_ROOM_ROUTE) {
        ComposeRoomScreen(
            viewModel = getViewModel(), onComposing = appBarStateChanged
        )
    }
    composable(COMPOSE_TASK_SUGGESTIONS_ROUTE) {
        ComposeViewTaskSuggestions(viewModel = getViewModel<ComposeTaskViewModel>(),
            appBarState = appBarStateChanged,
            roomId = it.arguments?.getLong(ParamKey.ROOM_ID_KEY)
                .let { id -> checkNotNull(id) { "Room id is null" } })
    }
    composable(
        route = COMPOSE_TASK_ROUTE,
        arguments = listOf(navArgument(ParamKey.ROOM_ID_KEY) { type = NavType.LongType })
    ) {
        val viewModel = getViewModel<ComposeTaskViewModel>()
        ComposeTaskScreen(viewModel = viewModel,
            regularities = viewModel.taskRegularities,
            onComposing = appBarStateChanged,
            roomId = it.arguments?.getLong(ParamKey.ROOM_ID_KEY)
                .let { id -> checkNotNull(id) { "Room id is null" } })
    }
}

@OptIn(ExperimentalAnimationApi::class)
private fun NavGraphBuilder.roomsFlowScreens(
    appBarStateChanged: (AppBarState) -> Unit
) {
    navigation(
        startDestination = ROOMS_ROUTE,
        route = ROOMS_FLOW_ROUTE,
    ) {
        composable(ROOMS_ROUTE) {
            RoomsScreen(
                viewModel = getViewModel<RoomsViewModel>(),
                onComposing = appBarStateChanged
            )
        }
        composable(
            route = ROOM_TASKS_ROUTE,
            arguments = listOf(navArgument(ParamKey.ROOM_ID_KEY) { type = NavType.LongType })
        ) {
            TasksListScreen(viewModel = getViewModel<TasksViewModel>(),
                onComposing = appBarStateChanged,
                roomId = it.arguments?.getLong(ParamKey.ROOM_ID_KEY)
                    .let { id -> checkNotNull(id) { "Room id is null" } })
        }
        composable(
            route = TASK_DETAILS_ROUTE,
            arguments = listOf(navArgument(ParamKey.TASK_ID_KEY) { type = NavType.LongType })
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