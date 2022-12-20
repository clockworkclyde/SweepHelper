package com.github.clockworkclyde.sweepyhelper.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.github.clockworkclyde.sweepyhelper.R
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import java.time.LocalDate

@Composable
fun DateTimePicker(state: MaterialDialogState, onDateChanged: (LocalDate) -> Unit) {
    MaterialDialog(
        dialogState = state,
        onCloseRequest = { state.hide() },
        buttons = {
            positiveButton(stringResource(R.string.date_positive_button))
            negativeButton(stringResource(R.string.date_negative_button))
        }) {

        datepicker { onDateChanged(it) }
    }
}

