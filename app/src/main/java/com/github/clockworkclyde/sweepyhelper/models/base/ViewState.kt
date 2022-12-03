package com.github.clockworkclyde.sweepyhelper.models.base

import com.github.clockworkclyde.sweepyhelper.ui.base.IViewState

sealed class ViewState<out T> : IViewState {
    data class Success<T>(val data: T) : ViewState<T>()
    object Empty : ViewState<Nothing>()
    object Loading : ViewState<Nothing>()
    data class Error(val exception: Exception) : ViewState<Nothing>()
}