package com.github.clockworkclyde.sweepyhelper.models.base

import com.github.clockworkclyde.sweepyhelper.ui.base.IViewState

data class ItemsViewState<out T>(val data: T, val isLoading: Boolean, val isError: Boolean) :
    IViewState