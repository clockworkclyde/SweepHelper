package com.github.clockworkclyde.sweepyhelper.ui.compose.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.github.clockworkclyde.sweepyhelper.R
import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.RoomType
import com.github.clockworkclyde.sweepyhelper.ui.compose.contract.ComposeRoomViewState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComposeViewNewRoom(
    state: ComposeRoomViewState,
    roomTypes: List<RoomType>,
    onTitleChanged: (String) -> Unit,
    onTypeChanged: (RoomType) -> Unit,
    onSaveButtonClicked: () -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Box {
            LazyColumn(content = {
                item {
                    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                        Text(text = stringResource(R.string.compose_room_title))
                        TextField(
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .fillMaxWidth(),
                            enabled = !state.inProgress,
                            value = state.title,
                            onValueChange = onTitleChanged
                        )
                    }
                }

                item {
                    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)) {
                        Text(text = stringResource(R.string.compose_room_type))

                        ExposedDropdownMenuBox(
                            modifier = Modifier.padding(top = 4.dp),
                            expanded = isExpanded,
                            onExpandedChange = {
                                isExpanded = !isExpanded
                            }) {
                            TextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .onGloballyPositioned { coordinates ->
                                        textFieldSize = coordinates.size.toSize()
                                    },
                                value = stringResource(state.type.titleId),
                                onValueChange = {},
                                readOnly = true,
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                                }, colors = ExposedDropdownMenuDefaults.textFieldColors()
                            )
                            DropdownMenu(
                                modifier = Modifier.width(with(LocalDensity.current) { textFieldSize.width.toDp() }),
                                expanded = isExpanded,
                                onDismissRequest = { isExpanded = false }) {
                                roomTypes.forEach {
                                    DropdownMenuItem(onClick = {
                                        onTypeChanged(it)
                                        isExpanded = false
                                    }) {
                                        Text(text = stringResource(it.titleId))
                                    }
                                }
                            }
                        }
                    }
                }
            })

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