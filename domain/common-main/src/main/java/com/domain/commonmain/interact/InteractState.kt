package com.domain.commonmain.interact

sealed class InteractState {
    object Started : InteractState()
    object Success : InteractState()
    data class Error(val throwable: Throwable) : InteractState()
}