package com.feature.category

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.core.common.model.models.category.CategoriesItem
import com.core.common.ui.ui.BaseViewModel
import com.domain.commonmain.interact_result.InteractResultState
import com.domain.commonmain.interactors.GetListCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val getListCategoriesUseCase: GetListCategoriesUseCase) :
    BaseViewModel<List<CategoriesItem>>() {
    private var job: Job? = null
    private var page = 1
    private var isLoading = false

    init {
        getListCategories()
    }


    override var _data: MutableStateFlow<List<CategoriesItem>> = MutableStateFlow(emptyList())

    private fun getListCategories() {
        job?.cancel()
        job = viewModelScope.launch {
            getListCategoriesUseCase(GetListCategoriesUseCase.Params(page)).collectLatest { it.open() }
        }
    }

    fun nextPage() {
        if (!isLoading) {
            page++
            getListCategories()
            loading()
        }
    }

    private fun InteractResultState<List<CategoriesItem>>.open() {
        when (this) {
            InteractResultState.Error -> _uiState.value = UiState.FAIL
            InteractResultState.Loading -> {}
            is InteractResultState.Success -> { _data.value += data
                _uiState.value = UiState.SUCCESS
                if (isLoading && data.isNotEmpty()) loading()
            }
        }
    }

    private fun loading() {
        isLoading = !isLoading
    }


    fun retryData() {
        page = 1
        getListCategories()
    }
}