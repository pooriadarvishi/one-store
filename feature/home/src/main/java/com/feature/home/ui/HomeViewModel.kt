package com.feature.home.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.core.common.enums.common.OrderingFilters
import com.core.common.enums.common.ui
import com.core.common.model.models.products.ProductsItem
import com.core.common.ui.ui.BaseViewModel
import com.domain.commonmain.interact_result.InteractResultState
import com.domain.commonmain.interactors.GetListProductsUseCase
import com.feature.home.util.ItemRes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getListProductsUseCase: GetListProductsUseCase,
    private val orderingFilters: OrderingFilters
) : BaseViewModel<List<ItemRes>>() {


    private val lastedParams = GetListProductsUseCase.Params(
        1, orderingFilters.orderById(), orderingFilters.orderDefault()
    )
    private val popularParams = GetListProductsUseCase.Params(
        1, orderingFilters.orderByPopularity(), orderingFilters.orderDefault()
    )
    private val bestParams = GetListProductsUseCase.Params(
        1, orderingFilters.orderByRating(), orderingFilters.orderDefault()
    )

    private val lastedProducts = getListProductsUseCase(lastedParams).stateIn(
        viewModelScope, SharingStarted.Eagerly, InteractResultState.Loading
    )
    private val popularityProducts = getListProductsUseCase(popularParams).stateIn(
        viewModelScope, SharingStarted.Eagerly, InteractResultState.Loading
    )
    private val bestProducts = getListProductsUseCase(bestParams).stateIn(
        viewModelScope, SharingStarted.Eagerly, InteractResultState.Loading
    )


    override var _data: MutableStateFlow<List<ItemRes>> = MutableStateFlow(emptyList())


    init {
        setData()
    }


    private fun setData() {
        combine(lastedProducts, popularityProducts, bestProducts) { lasted, popular, best ->
            listOf(
                ItemRes.createItemRes(lastedParams.orderBy.ui(lastedParams.order), lasted.open()),
                ItemRes.createItemRes(
                    popularParams.orderBy.ui(popularParams.order), popular.open()
                ),
                ItemRes.createItemRes(bestParams.orderBy.ui(bestParams.order), best.open())
            )
        }.onEach { _data.value = it }
            .launchIn(viewModelScope)
    }

    private fun InteractResultState<List<ProductsItem>>.open(): List<ProductsItem> {
        when (this) {
            InteractResultState.Error -> _uiState.value = UiState.FAIL
            InteractResultState.Loading -> {}
            is InteractResultState.Success -> {
                _uiState.value = UiState.SUCCESS
                return data
            }
        }
        return emptyList()
    }
}