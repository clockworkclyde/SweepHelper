package com.github.clockworkclyde.sweepyhelper.ui.compose.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.clockworkclyde.sweepyhelper.R
import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.Room
import com.github.clockworkclyde.sweepyhelper.models.ui.tasks.Task
import com.github.clockworkclyde.sweepyhelper.ui.compose.ComposeViewState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ComposeViewNewTask(state: ComposeViewState<Room, List<Task>>) {
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
            })
        }
    }
}