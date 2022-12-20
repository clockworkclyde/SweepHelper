package com.github.clockworkclyde.sweepyhelper.ui.compose

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.clockworkclyde.sweepyhelper.models.ui.base.AppBarState
import com.github.clockworkclyde.sweepyhelper.models.ui.tasks.Regularity
import com.github.clockworkclyde.sweepyhelper.ui.composables.DateTimePicker
import com.github.clockworkclyde.sweepyhelper.ui.compose.contract.ComposeTaskViewEvent
import com.github.clockworkclyde.sweepyhelper.ui.compose.views.ComposeTaskCard
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

@Composable
fun ComposeTaskScreen(
    viewModel: ComposeTaskViewModel,
    regularities: List<Regularity>,
    onComposing: (AppBarState) -> Unit,
    roomId: Long
) {
    val state = viewModel.viewState.collectAsState().value

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
                ComposeTaskCard(
                    interactionSource = interactionSource,
                    regularities = regularities.map { stringResource(it.titleId) },
                    state = state,
                    onTitleChanged = { viewModel.setEvent(ComposeTaskViewEvent.OnTitleChanged(it)) },
                    onRepeatModeSwitchedTo = {
                        viewModel.setEvent(ComposeTaskViewEvent.OnRepeatModeSwitchedTo(it))
                    },
                    onRegularityQuantityChanged = {
                        viewModel.setEvent(ComposeTaskViewEvent.OnRegularityQuantityChanged(it))
                    },
                    onRegularityItemChanged = {
                        viewModel.setEvent(
                            ComposeTaskViewEvent.OnRegularityItemChanged(regularities[it])
                        )
                    },
                    onCreateTaskClicked = { viewModel.setEvent(ComposeTaskViewEvent.CreateTaskButtonClicked) }
                )
            })
        }

        DateTimePicker(
            state = dialogState,
            onDateChanged = { viewModel.setEvent(ComposeTaskViewEvent.OnStartDateChanged(it)) })
    }

    LaunchedEffect(key1 = true, block = {
        onComposing(AppBarState(title = state.title))
    })
}