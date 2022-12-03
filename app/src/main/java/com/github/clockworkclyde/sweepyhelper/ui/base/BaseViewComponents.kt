package com.github.clockworkclyde.sweepyhelper.ui.base

interface ViewState

interface ViewEffect

interface ViewEvent

sealed class BaseViewState<out T> : ViewState {
    data class Success<T>(val data: T) : BaseViewState<T>()
    object Empty : BaseViewState<Nothing>()
    object Loading : BaseViewState<Nothing>()
    data class Error(val exception: Exception) : BaseViewState<Nothing>()
}