package com.feature.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.enums.common.OrderingFilters
import com.core.common.enums.common.ui
import com.core.common.model.models.products.ProductsItem
import com.domain.commonmain.interact_result.InteractResultState
import com.domain.commonmain.interactors.GetListProductsUseCase
import com.feature.home.util.ItemRes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getListProductsUseCase: GetListProductsUseCase,
    private val orderingFilters: OrderingFilters
) : ViewModel() {

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
        viewModelScope, SharingStarted.WhileSubscribed(5_000), InteractResultState.Loading
    )
    private val popularityProducts = getListProductsUseCase(popularParams).stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5_000), InteractResultState.Loading
    )
    private val bestProducts = getListProductsUseCase(bestParams).stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5_000), InteractResultState.Loading
    )


    private val listItem: Array<ItemRes> =
        Array(3) { ItemRes.createItemRes("Demo", InteractResultState.Loading) }
    private val _items: MutableStateFlow<List<ItemRes>> = MutableStateFlow(emptyList())
    val items: StateFlow<List<ItemRes>> = _items


    private fun getItems() {
        lastedProducts.open(lastedParams.orderBy.ui(lastedParams.order), 0)
        popularityProducts.open(popularParams.orderBy.ui(lastedParams.order), 1)
        bestProducts.open(bestParams.orderBy.ui(lastedParams.order), 2)
    }

    init {
        getItems()
    }

    private fun StateFlow<InteractResultState<List<ProductsItem>>>.open(title: String, index: Int) {
        viewModelScope.launch {
            this@open.collectLatest {
                listItem[index] = ItemRes.createItemRes(title, it)
                _items.value = listItem.toList()
            }
        }
    }
}