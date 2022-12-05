package com.github.clockworkclyde.sweepyhelper.ui.compose.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.github.clockworkclyde.sweepyhelper.R
import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.Room
import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.RoomType
import com.github.clockworkclyde.sweepyhelper.models.ui.tasks.Task
import com.github.clockworkclyde.sweepyhelper.ui.compose.ComposeViewState

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun ComposeViewNewRoom(
    state: ComposeViewState<Room, List<Task>>,
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
                stickyHeader {
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
                        text = stringResource(R.string.compose_new_room),
                        style = MaterialTheme.typography.h5
                    )
                }
                item {
                    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                        Text(text = stringResource(R.string.compose_room_title))
                        TextField(
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .fillMaxWidth(),
                            enabled = !state.inProgress,
                            value = state.room.title,
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
                                value = stringResource(state.room.type.titleId),
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

                item {
                    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)) {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            enabled = !state.inProgress,
                            onClick = { onSaveButtonClicked() }) {
                            Text(
                                text = stringResource(R.string.compose_room_save_btn),
                                style = MaterialTheme.typography.h6
                            )
                        }
                    }
                }
            })
        }
    }
}