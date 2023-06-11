package com.domain.commonmain.util

import com.domain.commonmain.interact_result.InteractResultState

fun <T> InteractResultState<T>.action(
    errorAction: () -> Unit, loadingAction: () -> Unit, successAction: (T) -> Unit
) {
    when (this) {
        is InteractResultState.Error -> errorAction()
        is InteractResultState.Loading -> loadingAction()
        is InteractResultState.Success -> successAction(data)
    }
}