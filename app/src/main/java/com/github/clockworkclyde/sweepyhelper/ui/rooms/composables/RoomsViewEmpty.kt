package com.github.clockworkclyde.sweepyhelper.ui.rooms.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.clockworkclyde.sweepyhelper.R

@Composable
fun RoomsViewEmpty(onClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    style = MaterialTheme.typography.h6,
                    text = stringResource(id = R.string.no_rooms_found)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Button(onClick = { onClick() }) {
                    Text(text = stringResource(id = R.string.btn_create_first_room))
                }
            }
        }
    }
}