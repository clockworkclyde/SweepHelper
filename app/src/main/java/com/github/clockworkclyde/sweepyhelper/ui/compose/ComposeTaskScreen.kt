package com.github.clockworkclyde.sweepyhelper.ui.compose

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.clockworkclyde.sweepyhelper.R
import com.github.clockworkclyde.sweepyhelper.models.ui.base.AppBarState
import com.github.clockworkclyde.sweepyhelper.models.ui.tasks.Regularity
import com.github.clockworkclyde.sweepyhelper.models.ui.tasks.Task
import com.github.clockworkclyde.sweepyhelper.ui.composables.DateTimePicker
import com.github.clockworkclyde.sweepyhelper.ui.composables.DropdownMenuByTextField
import com.github.clockworkclyde.sweepyhelper.ui.composables.HorizontalCenteredRow
import com.github.clockworkclyde.sweepyhelper.utils.checkIsRegularityQuantityValid
import com.github.clockworkclyde.sweepyhelper.utils.formatByDefaultDateFormatter
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate

@Composable
fun ComposeTaskScreen(
    viewModel: ComposeViewModel,
    regularities: List<Regularity>,
    onTaskCreated: (Task) -> Unit,
    onComposing: (AppBarState) -> Unit
) {
    val state = viewModel.viewState.collectAsState().value

    var taskTitle by remember { mutableStateOf("") }
    //var taskDesc by remember { mutableStateOf("") }
    var isOnRepeat by remember { mutableStateOf(false) }
    var regularityQuantity by remember { mutableStateOf("3") }
    var regularityIndex by remember { mutableStateOf(1) }
    val regularityNames = regularities.map { stringResource(it.titleId) }
    var startDate by remember { mutableStateOf<LocalDate>(LocalDate.now()) }

    val dialogState = rememberMaterialDialogState()
    val interactionSource = remember { MutableInteractionSource() }
    if (interactionSource.collectIsPressedAsState().value && !state.inProgress) {
        dialogState.show()
    }

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Box(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        ) {
            Column(content = {
//                stickyHeader {
//                    Text(
//                        text = state.room.title.replaceFirstChar { it.uppercase() },
//                        style = MaterialTheme.typography.h5
//                    )
//                }
                Card(elevation = 2.dp, shape = MaterialTheme.shapes.medium) {
                    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                        Text(
                            modifier = Modifier.padding(top = 16.dp),
                            text = stringResource(R.string.compose_task_title),
                            style = MaterialTheme.typography.h6
                        )
                        TextField(modifier = Modifier
                            .padding(top = 4.dp)
                            .fillMaxWidth(),
                            enabled = !state.inProgress,
                            value = taskTitle,
                            onValueChange = { taskTitle = it })
                        Text(
                            modifier = Modifier.padding(top = 16.dp),
                            text = stringResource(R.string.compose_task_start_date),
                            style = MaterialTheme.typography.h6
                        )
                        TextField(modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth(),
                            interactionSource = interactionSource,
                            readOnly = true,
                            value = startDate.formatByDefaultDateFormatter(),
                            onValueChange = {})
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = stringResource(R.string.compose_task_switch_regularity_mode),
                                style = MaterialTheme.typography.h6
                            )
                            Switch(checked = isOnRepeat, onCheckedChange = { isOnRepeat = it })
                        }
                        Text(text = stringResource(R.string.compose_task_regularity))
                        HorizontalCenteredRow(Modifier.padding(top = 8.dp, bottom = 16.dp)) {
                            TextField(modifier = Modifier.width(56.dp),
                                enabled = !state.inProgress && isOnRepeat,
                                value = regularityQuantity,
                                onValueChange = { value ->
                                    value.checkIsRegularityQuantityValid()
                                        ?.let { regularityQuantity = it }
                                })
                            DropdownMenuByTextField(modifier = Modifier
                                .weight(1f)
                                .padding(start = 8.dp),
                                items = regularityNames,
                                initialValue = regularityNames[regularityIndex],
                                enabled = isOnRepeat,
                                onItemClicked = { regularityIndex = it })
                        }

                        Button(modifier = Modifier.fillMaxWidth(), onClick = {
                            onTaskCreated.invoke(
                                Task.create(
                                    title = taskTitle,
                                    owner = state.room.id,
                                    startDate = startDate,
                                    regularity = regularities[regularityIndex],
                                    regularityQuantity = regularityQuantity.toInt()
                                )
                            )
                        }) {
                            Text(
                                text = stringResource(R.string.compose_task_create_button),
                                style = MaterialTheme.typography.h6
                            )
                        }
                        Spacer(Modifier.height(16.dp))
                    }
                }
            })
        }

        DateTimePicker(state = dialogState, onDateChanged = { startDate = it })
    }

    LaunchedEffect(key1 = true, block = {
        onComposing(AppBarState(title = state.room.title))
    })
}