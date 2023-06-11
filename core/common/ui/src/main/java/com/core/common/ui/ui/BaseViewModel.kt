package com.core.common.ui.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


abstract class BaseViewModel<T> : ViewModel() {
    protected abstract var _data: MutableStateFlow<T>
    val data: StateFlow<T> get() = _data


    protected val _uiState = MutableStateFlow(UiState.LOADING)
    val uiState: StateFlow<UiState> get() = _uiState



    enum class UiState {
        SUCCESS, FAIL, LOADING
    }
}