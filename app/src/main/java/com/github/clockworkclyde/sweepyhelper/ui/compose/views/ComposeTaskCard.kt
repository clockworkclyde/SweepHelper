package com.github.clockworkclyde.sweepyhelper.ui.compose.views

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.clockworkclyde.sweepyhelper.R
import com.github.clockworkclyde.sweepyhelper.ui.composables.DropdownMenuByTextField
import com.github.clockworkclyde.sweepyhelper.ui.composables.HorizontalCenteredRow
import com.github.clockworkclyde.sweepyhelper.ui.compose.contract.ComposeTaskViewState
import com.github.clockworkclyde.sweepyhelper.utils.formatByDefaultDateFormatter

@Composable
fun ComposeTaskCard(
    interactionSource: MutableInteractionSource,
    regularities: List<String>,
    state: ComposeTaskViewState,
    onTitleChanged: (String) -> Unit,
    onRepeatModeSwitchedTo: (Boolean) -> Unit,
    onRegularityQuantityChanged: (Int) -> Unit,
    onRegularityItemChanged: (Int) -> Unit,
    onCreateTaskClicked: () -> Unit
) {
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
                value = state.title,
                onValueChange = { onTitleChanged(it) })
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
                value = state.startDate.formatByDefaultDateFormatter(),
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
                Switch(
                    checked = state.isOnRepeatMode,
                    onCheckedChange = { onRepeatModeSwitchedTo(it) })
            }
            Text(text = stringResource(R.string.compose_task_regularity))
            HorizontalCenteredRow(Modifier.padding(top = 8.dp, bottom = 16.dp)) {
                TextField(modifier = Modifier.width(56.dp),
                    enabled = !state.inProgress && state.isOnRepeatMode,
                    value = state.regularityQuantity.toString(),
                    onValueChange = { value ->
                        when (val it = value.toIntOrNull()) {
                            null -> {}
                            else -> onRegularityQuantityChanged(it)
                        }
                    })
                DropdownMenuByTextField(modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                    items = regularities,
                    initialValue = stringResource(state.regularity.titleId),
                    enabled = state.isOnRepeatMode && !state.inProgress,
                    onItemClicked = { onRegularityItemChanged(it) })
            }

            Button(modifier = Modifier.fillMaxWidth(), onClick = { onCreateTaskClicked() }) {
                Text(
                    text = stringResource(R.string.compose_task_create_button),
                    style = MaterialTheme.typography.h6
                )
            }
            Spacer(Modifier.height(16.dp))
        }
    }
}