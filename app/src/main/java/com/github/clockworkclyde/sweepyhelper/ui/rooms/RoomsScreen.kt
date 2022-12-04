package com.github.clockworkclyde.sweepyhelper.ui.rooms

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.github.clockworkclyde.sweepyhelper.ui.rooms.composables.RoomsViewContentList
import com.github.clockworkclyde.sweepyhelper.ui.rooms.composables.RoomsViewEmpty
import com.github.clockworkclyde.sweepyhelper.ui.rooms.composables.RoomsViewLoading

@Composable
fun RoomsScreen(viewModel: RoomsViewModel) {
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
                    // Navigate to compose new room
                }
            }
        }
    }

    LaunchedEffect(key1 = viewState, block = {
        viewModel.setEvent(RoomsViewEvent.EnterScreen)
    })
}