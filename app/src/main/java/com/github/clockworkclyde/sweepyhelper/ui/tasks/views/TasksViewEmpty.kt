package com.github.clockworkclyde.sweepyhelper.ui.tasks.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.clockworkclyde.sweepyhelper.R
import com.github.clockworkclyde.sweepyhelper.ui.theme.SweepyHelperTheme

@Composable
fun TasksViewEmpty(onFabClicked: () -> Unit) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(48.dp),
                    imageVector = Icons.Default.Info,
                    contentDescription = "Room doesn't contains any tasks"
                )
                Text(
                    text = stringResource(R.string.no_tasks_found),
                    style = MaterialTheme.typography.h6
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    style = MaterialTheme.typography.subtitle2,
                    text = stringResource(R.string.suggestion_for_empty_room)
                )
            }
            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(24.dp),
                onClick = onFabClicked
            ) {
                Image(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(32.dp),
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add new task"
                )
            }
        }
    }
}

@Preview
@Composable
fun TasksViewEmptyPreview() {
    SweepyHelperTheme(darkTheme = true) {
        TasksViewEmpty(onFabClicked = { })
    }
}