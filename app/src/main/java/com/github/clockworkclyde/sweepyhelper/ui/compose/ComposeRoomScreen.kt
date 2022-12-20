package com.github.clockworkclyde.sweepyhelper.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.github.clockworkclyde.sweepyhelper.R
import com.github.clockworkclyde.sweepyhelper.models.ui.base.AppBarState
import com.github.clockworkclyde.sweepyhelper.ui.compose.views.ComposeViewNewRoom

@Composable
fun ComposeRoomScreen(
    navController: NavController,
    viewModel: ComposeViewModel,
    onComposing: (AppBarState) -> Unit
) {
    val state = viewModel.viewState.collectAsState().value
    when {
        state.isError -> {
            TODO()
        }
        state.isDataSaved -> {
            navController.navigateUp()
        }
        else -> {
            ComposeViewNewRoom(
                state = state,
                onTitleChanged = { viewModel.setEvent(ComposeViewEvent.RoomTitleChanged(it)) },
                onTypeChanged = { viewModel.setEvent(ComposeViewEvent.RoomTypeChanged(it)) },
                onSaveButtonClicked = { viewModel.setEvent(ComposeViewEvent.CreateRoomButtonClicked) },
                roomTypes = viewModel.roomTypes
            )
        }
    }

    val context = LocalContext.current
    LaunchedEffect(true) {
        onComposing(AppBarState(title = context.getString(R.string.compose_new_room)))
    }
}