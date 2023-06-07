package com.example.common_main.result

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

fun <T> Flow<T>.asResponseState(): Flow<ResponseState<T>> {
    return this.map<T, ResponseState<T>> {
        ResponseState.Success(it)
    }.catch { emit(ResponseState.Error(it)) }
}

fun <T> Flow<ResponseState<T>>.open(): Flow<T> = map { it.pure() }

fun <T> ResponseState<T>.pure(): T = when (this) {
    is ResponseState.Error -> throw exception
    is ResponseState.Success -> data
}