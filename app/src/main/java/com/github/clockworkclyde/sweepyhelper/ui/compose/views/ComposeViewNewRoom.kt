package com.github.clockworkclyde.sweepyhelper.ui.compose.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.clockworkclyde.sweepyhelper.R
import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.RoomType
import com.github.clockworkclyde.sweepyhelper.ui.composables.DropdownMenuByTextField
import com.github.clockworkclyde.sweepyhelper.ui.compose.contract.ComposeRoomViewState

@Composable
fun ComposeViewNewRoom(
    state: ComposeRoomViewState,
    roomTypes: List<RoomType>,
    onTitleChanged: (String) -> Unit,
    onTypeChanged: (RoomType) -> Unit,
    onSaveButtonClicked: () -> Unit
) {
    val context = LocalContext.current
    val roomTypeMenuItems = remember { roomTypes.map { context.getString(it.titleId) } }

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Box {
            Column(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)) {

                Text(text = stringResource(R.string.title_compose_room))
                TextField(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    enabled = !state.inProgress,
                    value = state.title,
                    onValueChange = onTitleChanged
                )
                Spacer(Modifier.height(16.dp))

                Text(text = stringResource(R.string.compose_room_type))
                DropdownMenuByTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    items = roomTypeMenuItems,
                    initialValue = stringResource(state.type.titleId),
                    onItemClicked = { onTypeChanged(roomTypes[it]) })
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                enabled = !state.inProgress,
                onClick = { onSaveButtonClicked() }) {
                Text(
                    text = stringResource(R.string.compose_room_save_btn),
                    style = MaterialTheme.typography.h6
                )
            }

        }
    }
}