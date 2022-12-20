package com.github.clockworkclyde.sweepyhelper.ui.compose

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.clockworkclyde.sweepyhelper.R
import com.github.clockworkclyde.sweepyhelper.models.ui.base.AppBarState
import com.github.clockworkclyde.sweepyhelper.models.ui.tasks.Task
import com.github.clockworkclyde.sweepyhelper.ui.composables.DateTimePicker
import com.github.clockworkclyde.sweepyhelper.ui.composables.ExpandableCardCheckbox
import com.github.clockworkclyde.sweepyhelper.utils.formatByDefaultDateFormatter
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

@Composable
fun ComposeViewTaskSuggestions(
    viewModel: ComposeViewModel,
    onSaveTasksClicked: (List<Long>) -> Unit,
    appBarState: (AppBarState) -> Unit
) {

    val viewState = viewModel.viewState.collectAsState()
    val state = viewState.value
    val suggestions = viewModel.suggestions.collectAsState()
    val selectedTasks = remember { mutableStateListOf<Long>() }

    val dialogState = rememberMaterialDialogState()
    var currentTask by remember { mutableStateOf<Task?>(null) }

    LaunchedEffect(key1 = viewState, block = {
        viewModel.setEvent(ComposeViewEvent.RequestTaskSuggestionsForRoom(state.room.id))
    })

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Box(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            LazyColumn {
                itemsIndexed(suggestions.value) { _, task: Task ->
                    ExpandableCardCheckbox(
                        title = task.title,
                        titleTextStyle = MaterialTheme.typography.h6,
                        checked = selectedTasks.contains(task.id),
                        onCheckBoxClicked = {
                            task.id.let {
                                if (selectedTasks.contains(it)) {
                                    selectedTasks.remove(it)
                                } else selectedTasks.add(it)
                            }
                        }) {
                        Text(
                            modifier = Modifier.padding(top = 16.dp),
                            text = stringResource(R.string.compose_task_start_date),
                            style = MaterialTheme.typography.h6
                        )
                        TextField(modifier = Modifier
                            .padding(top = 8.dp)
                            .fillMaxWidth(),
                            interactionSource = remember { MutableInteractionSource() }.also { source ->
                                LaunchedEffect(source) {
                                    source.interactions.collect {
                                        if (it is PressInteraction.Release) {
                                            dialogState.show()
                                            println(dialogState.showing)
                                            currentTask = if (dialogState.showing) {
                                                task
                                            } else null
                                        }
                                    }
                                }
                            },
                            readOnly = true,
                            value = task.startDate.formatByDefaultDateFormatter(),
                            onValueChange = {})
                    }
                }
            }

            DateTimePicker(
                state = dialogState,
                onDateChanged = { date ->
                    currentTask?.let {
                        println(it.toString())
                        viewModel.setEvent(
                            ComposeViewEvent.UpdateTaskSuggestion(
                                it.copy(startDate = date)
                            )
                        )
                    }
                })
        }
    }
}
