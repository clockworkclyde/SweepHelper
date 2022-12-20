package com.github.clockworkclyde.sweepyhelper.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.github.clockworkclyde.sweepyhelper.R
import com.github.clockworkclyde.sweepyhelper.models.ui.base.AppBarState
import com.github.clockworkclyde.sweepyhelper.ui.compose.contract.ComposeRoomViewEvent
import com.github.clockworkclyde.sweepyhelper.ui.compose.views.ComposeViewNewRoom

@Composable
fun ComposeRoomScreen(
    viewModel: ComposeRoomViewModel,
    onComposing: (AppBarState) -> Unit
) {
    val state = viewModel.viewState.collectAsState().value
    when {
        state.isError -> {
            TODO()
        }
        else -> {
            ComposeViewNewRoom(
                state = state,
                onTitleChanged = { viewModel.setEvent(ComposeRoomViewEvent.RoomTitleChanged(it)) },
                onTypeChanged = { viewModel.setEvent(ComposeRoomViewEvent.RoomTypeChanged(it)) },
                onSaveButtonClicked = { viewModel.setEvent(ComposeRoomViewEvent.CreateRoomButtonClicked) },
                roomTypes = viewModel.roomTypes
            )
        }
    }

    val context = LocalContext.current
    LaunchedEffect(true) {
        onComposing(AppBarState(title = context.getString(R.string.compose_new_room)))
    }
}