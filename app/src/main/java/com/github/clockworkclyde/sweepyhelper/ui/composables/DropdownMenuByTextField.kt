package com.github.clockworkclyde.sweepyhelper.ui.composables

import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.toSize

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropdownMenuByTextField(
    modifier: Modifier = Modifier,
    items: List<String>,
    initialValue: String,
    onItemClicked: (Int) -> Unit,
    enabled: Boolean = true,
) {
    var isExpanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = {
            isExpanded = !isExpanded
        }) {
        TextField(
            modifier = modifier
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            enabled = enabled,
            value = initialValue,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            }, colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        DropdownMenu(
            modifier = Modifier.width(with(LocalDensity.current) { textFieldSize.width.toDp() }),
            expanded = isExpanded && enabled,
            onDismissRequest = { isExpanded = false }) {
            items.forEachIndexed { index, str ->
                DropdownMenuItem(onClick = {
                    onItemClicked(index); isExpanded = false
                }) {
                    Text(str)
                }
            }
        }
    }
}