package com.feature.products.ui.products

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.core.common.enums.common.OrderingFilters
import com.core.common.enums.common.asString
import com.core.common.enums.common.network
import com.core.common.model.models.products.ProductsItem
import com.core.common.ui.ui.BaseViewModel
import com.domain.commonmain.interact_result.InteractResultState
import com.domain.commonmain.interactors.GetListProductsByCategoryUseCase
import com.domain.commonmain.interactors.GetListProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getListProductsUseCase: GetListProductsUseCase,
    private val getListProductsByCategoryUseCase: GetListProductsByCategoryUseCase,
    private val orderingFilters: OrderingFilters
) : BaseViewModel<List<ProductsItem>>() {
    private var job: Job? = null
    private var categoryId: Int = 0
    private var page = 1
    private var orderBy = orderingFilters.orderByDefault()
    private var order = orderingFilters.orderDefault()
    private var isLoading = false


    override var _data =
        MutableStateFlow<List<ProductsItem>>(mutableListOf())


    operator fun invoke() {
        job?.cancel()
        job = if (categoryId != 0) {
            Log.e("Pooria", "invoke: Cat ", )
            getListProductByCategory()
        } else {
            Log.e("Pooria", "invoke: Mat", )

            getListProductByOrder()
        }
    }

    fun nextPage() {
        if (!isLoading) {
            page++
            invoke()
            loading()
        }
    }

    private fun getListProductByOrder() = viewModelScope.launch {
        getListProductsUseCase(
            GetListProductsUseCase.Params(
                page,
                orderBy,
                order
            )
        ).collectLatest { it.open() }
    }

    private fun getListProductByCategory(
    ) =
        viewModelScope.launch {
            getListProductsByCategoryUseCase(
                GetListProductsByCategoryUseCase.Params(
                    categoryId,
                    page,
                    orderBy,
                    order
                )
            ).collectLatest { it.open() }
        }


    private fun InteractResultState<List<ProductsItem>>.open() {

        when (this) {
            InteractResultState.Error -> _uiState.value = UiState.FAIL
            InteractResultState.Loading -> {}
            is InteractResultState.Success -> {
                _data.value += data
                _uiState.value = UiState.SUCCESS
                if (isLoading && data.isNotEmpty()) loading()
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


    private fun loading() {
        isLoading = !isLoading
    }

}