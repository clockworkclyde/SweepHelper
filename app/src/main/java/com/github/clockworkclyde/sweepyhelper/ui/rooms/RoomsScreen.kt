package com.github.clockworkclyde.sweepyhelper.ui.rooms

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.github.clockworkclyde.sweepyhelper.R
import com.github.clockworkclyde.sweepyhelper.models.ui.base.AppBarState
import com.github.clockworkclyde.sweepyhelper.ui.rooms.views.RoomsViewContentList
import com.github.clockworkclyde.sweepyhelper.ui.rooms.views.RoomsViewEmpty
import com.github.clockworkclyde.sweepyhelper.ui.rooms.views.RoomsViewLoading

@Composable
fun RoomsScreen(
    navController: NavController,
    viewModel: RoomsViewModel,
    onComposing: (AppBarState) -> Unit
) {
    val viewState = viewModel.viewState.collectAsState()
    val state = viewState.value
    when {
        state.isLoading -> RoomsViewLoading()
        state.isError -> TODO()
        else -> {
            if (state.data.isNotEmpty()) {
                RoomsViewContentList(
                    items = state.data,
                    onRoomClicked = { viewModel.setEvent(RoomsViewEvent.OnRoomClicked(it)) })
            } else {
                RoomsViewEmpty {
                    viewModel.setEvent(RoomsViewEvent.OnCreateNewRoomClicked)
                }
            }
        }
    }

    LaunchedEffect(viewState) {
        viewModel.setEvent(RoomsViewEvent.EnterScreen)
    }

    val context = LocalContext.current
    LaunchedEffect(true) {
        onComposing(AppBarState(title = context.getString(R.string.title_rooms_screen)))
    }
}