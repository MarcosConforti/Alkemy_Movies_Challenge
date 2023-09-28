package com.example.alkemymovieschallenge.core.domain

sealed class NetworkState<out T> {
    object Loading : NetworkState<Nothing>()
    data class Success<out T>(val data: T): NetworkState<T>()
    data class Error(val error: Throwable): NetworkState<Nothing>()
}
