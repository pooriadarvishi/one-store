package com.feature.category

import androidx.lifecycle.viewModelScope
import com.core.common.model.models.category.CategoriesItem
import com.core.common.ui.ui.BaseViewModel
import com.domain.commonmain.interact_result.InteractResultState
import com.domain.commonmain.interactors.GetListCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val getListCategoriesUseCase: GetListCategoriesUseCase) :
    BaseViewModel<List<CategoriesItem>>() {
    private var page = 1

    init {
        getListCategories()
    }


    override var _data: MutableStateFlow<List<CategoriesItem>> = MutableStateFlow(emptyList())

    private fun getListCategories() {
        viewModelScope.launch {
            getListCategoriesUseCase(GetListCategoriesUseCase.Params(page)).collectLatest { it.open() }
        }
    }

    fun nextPage() {
        page++
        getListCategories()
    }

    private fun InteractResultState<List<CategoriesItem>>.open() {
        when (this) {
            InteractResultState.Error -> _uiState.value = UiState.FAIL
            InteractResultState.Loading -> {}
            is InteractResultState.Success -> {
                _data.value += data
                _uiState.value = UiState.SUCCESS
            }
        }
    }
}