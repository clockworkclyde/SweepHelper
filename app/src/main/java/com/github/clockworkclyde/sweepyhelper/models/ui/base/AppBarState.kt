package com.github.clockworkclyde.sweepyhelper.models.ui.base

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable

data class AppBarState(val title: String, val actions: (@Composable RowScope.() -> Unit)? = null)