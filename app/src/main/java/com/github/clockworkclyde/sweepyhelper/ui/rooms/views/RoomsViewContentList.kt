package com.github.clockworkclyde.sweepyhelper.ui.rooms.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.Room
import com.github.clockworkclyde.sweepyhelper.models.ui.rooms.RoomType

@Composable
fun RoomsViewContentList(items: List<Room>, onRoomClicked: (Room) -> Unit) {
    val lazyListState = rememberLazyListState()

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Box {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                state = lazyListState,
                content = {
                    items(items) { item ->
                        RoomsViewContentCard(item = item, onRoomClicked = { onRoomClicked(it) })
                    }
                })
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RoomsViewContentCard(item: Room, onRoomClicked: (Room) -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth(),
        elevation = 2.dp,
        shape = MaterialTheme.shapes.medium,
        onClick = { onRoomClicked(item) }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                val condition = item.condition.value.toFloat()
                CircularProgressIndicator(
                    modifier = Modifier.size(32.dp),
                    color = if (condition < 0) Color.DarkGray else MaterialTheme.colors.secondary,
                    progress = condition
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = item.title,
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}

@Composable
@Preview
private fun RoomsViewContentCardPreview() {
    RoomsViewContentCard(item = Room(title = "Kitchen", type = RoomType.Kitchen)) {}
}