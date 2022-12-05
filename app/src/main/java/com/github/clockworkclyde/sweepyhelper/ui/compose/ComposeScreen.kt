package com.github.clockworkclyde.sweepyhelper.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.github.clockworkclyde.sweepyhelper.ui.compose.composables.ComposeViewNewRoom
import com.github.clockworkclyde.sweepyhelper.ui.compose.composables.ComposeViewNewTask

@Composable
fun ComposeScreen(navController: NavController, viewModel: ComposeViewModel) {
    val state = viewModel.viewState.collectAsState().value
    when {
        state.isError -> {}
        state.isRoomExisting -> {
            if (state.tasks.isNotEmpty()) {
                // Display tasks list with fab to create another one, or click on one of them to edit
            } else ComposeViewNewTask(state = state)
        }
        else -> ComposeViewNewRoom(
            state = state,
            onTitleChanged = { viewModel.handleEvents(ComposeViewEvent.RoomTitleChanged(it)) },
            onTypeChanged = { viewModel.handleEvents(ComposeViewEvent.RoomTypeChanged(it)) },
            onSaveButtonClicked = { viewModel.handleEvents(ComposeViewEvent.SaveButtonClicked) },
            roomTypes = viewModel.roomTypes
        )
    }
}