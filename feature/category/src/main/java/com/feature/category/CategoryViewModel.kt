package com.feature.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.model.models.category.CategoriesItem
import com.domain.commonmain.interact_result.InteractResultState
import com.domain.commonmain.interactors.GetListCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val getListCategoriesUseCase: GetListCategoriesUseCase) :
    ViewModel() {
    private var page = 1

    init {
        getListCategories()
    }


    private val _categories = MutableStateFlow<List<CategoriesItem>>(emptyList())
    val categories: StateFlow<List<CategoriesItem>> = _categories

    private val _stateNetwork =
        MutableStateFlow(StateNetwork.LOADING)
    val stateNetwork: StateFlow<StateNetwork> = _stateNetwork

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
            InteractResultState.Error -> _stateNetwork.value = StateNetwork.FAIL
            InteractResultState.Loading -> {}
            is InteractResultState.Success -> {
                _categories.value += data
                _stateNetwork.value = StateNetwork.SUCCESS
            }
        }
    }


    enum class StateNetwork {
        SUCCESS, FAIL, LOADING
    }
}