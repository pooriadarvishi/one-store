package com.example.common_main.result

sealed interface ResponseState<out T> {
    data class Success<T>(val data: T) : ResponseState<T>
    data class Error(val exception: Throwable) : ResponseState<Nothing>
}

