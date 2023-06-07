package com.example.common_main.result

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

fun <T> Flow<T>.asResponseState(): Flow<ResponseState<T>> {
    return this
        .map<T, ResponseState<T>> {
            ResponseState.Success(it)
        }
        .catch { emit(ResponseState.Error(it)) }
}

fun <T> openResponseState(responseState: ResponseState<T>): T {
    return when (responseState) {
        is ResponseState.Error -> throw responseState.exception
        is ResponseState.Success -> responseState.data
    }
}