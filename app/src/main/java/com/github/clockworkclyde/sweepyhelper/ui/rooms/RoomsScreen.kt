package com.github.clockworkclyde.sweepyhelper.ui.rooms

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.github.clockworkclyde.sweepyhelper.models.base.ViewState
import com.github.clockworkclyde.sweepyhelper.ui.rooms.composables.RoomsViewContentList
import com.github.clockworkclyde.sweepyhelper.ui.rooms.composables.RoomsViewEmpty
import com.github.clockworkclyde.sweepyhelper.ui.rooms.composables.RoomsViewLoading

@Composable
fun RoomsScreen(viewModel: RoomsViewModel) {
    val viewState = viewModel.viewState.collectAsState()
    when (val state = viewState.value) {
        is ViewState.Empty -> RoomsViewEmpty { }
        is ViewState.Error -> TODO()
        is ViewState.Loading -> RoomsViewLoading()
        is ViewState.Success -> RoomsViewContentList(items = state.data)
    }

    LaunchedEffect(key1 = viewState, block = {
        viewModel.setEvent(RoomsViewEvent.EnterScreen)
    })
}