package com.domain.commonmain.interact_result


sealed class InteractResultState<out T> {
    data class Success<T>(val data: T) : InteractResultState<T>()
    object Error : InteractResultState<Nothing>()
    object Loading : InteractResultState<Nothing>()
}