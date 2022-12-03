package com.github.clockworkclyde.sweepyhelper.models.base

sealed class Response<out T> {
    data class Success<T>(val data: T) : Response<T>()
    data class Error(val exception: Exception) : Response<Nothing>()
    object Empty : Response<Nothing>()
}