package com.github.clockworkclyde.sweepyhelper.ui.composables

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ExpandableCardCheckbox(
    title: String,
    titleTextStyle: TextStyle = LocalTextStyle.current,
    checked: Boolean = false,
    onCheckBoxClicked: () -> Unit,
    content: @Composable () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300, easing = LinearOutSlowInEasing
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(6f),
                    text = title,
                    style = titleTextStyle,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Checkbox(
                    modifier = Modifier.weight(1f),
                    checked = checked,
                    onCheckedChange = { onCheckBoxClicked() })
            }
            if (checked) {
                content()
            }
        }
    }
}

@Preview
@Composable
private fun SingleSelectionCards() {
    var checked by remember { mutableStateOf<Int>(0) }
    val list = IntRange(1, 10).map { it to "Task title" }

    LazyColumn(state = rememberLazyListState()) {
        items(list) { item ->
            ExpandableCardCheckbox(
                title = item.second,
                checked = checked == item.first,
                onCheckBoxClicked = {
                    checked = if (checked != item.first) {
                        item.first
                    } else -1
                }) {
                Text("12323232323232 2323232 3223232 323232 232323 23232 2323232 2323232 23232323")
            }
        }
    }
}

@Composable
@Preview
private fun MultipleSelectionCards() {
    val selectedIds = remember { mutableStateListOf<Int>() }
    val list = IntRange(1, 10).map { it to "Task title" }

    LazyColumn(state = rememberLazyListState()) {
        items(list) { item ->
            ExpandableCardCheckbox(
                title = item.second,
                checked = selectedIds.contains(item.first),
                onCheckBoxClicked = {
                    if (selectedIds.contains(item.first)) {
                        selectedIds.remove(item.first)
                    } else {
                        selectedIds.add(item.first)
                    }
                }) {
                Text("12323232323232 2323232 3223232 323232 232323 23232 2323232 2323232 23232323")
            }
        }
    }
}