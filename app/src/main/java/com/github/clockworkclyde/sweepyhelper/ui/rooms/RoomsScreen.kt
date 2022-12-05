package com.github.clockworkclyde.sweepyhelper.ui.rooms

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.github.clockworkclyde.sweepyhelper.ui.rooms.composables.RoomsViewContentList
import com.github.clockworkclyde.sweepyhelper.ui.rooms.composables.RoomsViewEmpty
import com.github.clockworkclyde.sweepyhelper.ui.rooms.composables.RoomsViewLoading

@Composable
fun RoomsScreen(navController: NavController, viewModel: RoomsViewModel) {
    val viewState = viewModel.viewState.collectAsState()
    val state = viewState.value
    when {
        state.isLoading -> RoomsViewLoading()
        state.isError -> TODO()
        else -> {
            if (state.data.isNotEmpty()) {
                RoomsViewContentList(items = state.data)
            } else {
                RoomsViewEmpty {
                    navController.navigate(route = "compose")
                }
            }
        }
    }

    LaunchedEffect(key1 = viewState, block = {
        viewModel.setEvent(RoomsViewEvent.EnterScreen)
    })
}