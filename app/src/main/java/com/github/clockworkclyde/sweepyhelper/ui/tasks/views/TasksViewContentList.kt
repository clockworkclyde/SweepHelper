package com.github.clockworkclyde.sweepyhelper.ui.tasks.views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.github.clockworkclyde.sweepyhelper.models.ui.tasks.Task
import com.github.clockworkclyde.sweepyhelper.ui.tasks.contract.TasksViewState

@Composable
fun TasksViewContentList(state: TasksViewState, onTaskClicked: (Task) -> Unit) {
    LazyColumn {
        items(state.tasks) { task ->
            TaskContentCard(
                title = task.title,
                onCardClicked = { onTaskClicked.invoke(task) }
            )
        }
    }
}