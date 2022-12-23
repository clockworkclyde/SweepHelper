package com.github.clockworkclyde.sweepyhelper.ui.tasks.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskContentCard(title: String, onCardClicked: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onCardClicked
    ) {
        Text(text = title)
    }
}