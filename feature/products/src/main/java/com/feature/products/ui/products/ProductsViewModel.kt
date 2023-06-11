package com.feature.products.ui.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.enums.common.OrderingFilters
import com.core.common.enums.common.asString
import com.core.common.enums.common.network
import com.core.common.model.models.products.ProductsItem
import com.domain.commonmain.interact_result.InteractResultState
import com.domain.commonmain.interactors.GetListProductsByCategoryUseCase
import com.domain.commonmain.interactors.GetListProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getListProductsUseCase: GetListProductsUseCase,
    private val getListProductsByCategoryUseCase: GetListProductsByCategoryUseCase,
    private val orderingFilters: OrderingFilters
) : ViewModel() {
    private var job: Job? = null
    private var categoryId: Int = 0
    private var page = 1
    private var orderBy = orderingFilters.orderByDefault()
    private var order = orderingFilters.orderDefault()


    private val _products =
        MutableStateFlow<List<ProductsItem>>(mutableListOf())
    val products: StateFlow<List<ProductsItem>> = _products

    private val _stateNetwork =
        MutableStateFlow(StateNetwork.LOADING)
    val stateNetwork: StateFlow<StateNetwork> = _stateNetwork


    operator fun invoke() {
        job?.cancel()
        if (categoryId != 0) {
            getListProductByCategory()
        } else {
            getListProductByOrder()
        }
    }

    fun nextPage() {
        page++
        getListProductByCategory()
    }

    private fun getListProductByOrder() {
        job = viewModelScope.launch {
            getListProductsUseCase(
                GetListProductsUseCase.Params(
                    page,
                    orderBy,
                    order
                )
            ).collectLatest { it.open() }
        }
    }

    private fun getListProductByCategory(
    ) {
        job = viewModelScope.launch {
            getListProductsByCategoryUseCase(
                GetListProductsByCategoryUseCase.Params(
                    categoryId,
                    page,
                    orderBy,
                    order
                )
            ).collectLatest { it.open() }
        }
    }


    private fun InteractResultState<List<ProductsItem>>.open() {

        when (this) {
            InteractResultState.Error -> _stateNetwork.value = StateNetwork.FAIL
            InteractResultState.Loading -> {}
            is InteractResultState.Success -> {
                _products.value += data
                _stateNetwork.value = StateNetwork.SUCCESS
            }
        }
    }


    fun setOrder(pureOrder: String) {
        val orders = pureOrder.network()
        orderBy = orders.first
        order = orders.second
    }

    fun setCategoryId(categoryId: Int) {
        this.categoryId = categoryId
    }

    fun getOrderDDefault(): String = orderingFilters.orderByDefault().asString()


    enum class StateNetwork {
        SUCCESS, FAIL, LOADING
    }
}