package com.feature.products.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.enums.enums.OrderByFilter
import com.core.common.enums.enums.OrderFilter
import com.core.common.model.models.products.ProductsItem
import com.domain.commonmain.interact_result.InteractResultState
import com.domain.commonmain.interactors.GetListProductsByCategoryUseCase
import com.domain.commonmain.interactors.GetListProductsUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductsViewModel @Inject constructor(
    private val getListProductsUseCase: GetListProductsUseCase,
    private val getListProductsByCategoryUseCase: GetListProductsByCategoryUseCase
) :
    ViewModel() {
    private val _products = MutableLiveData<InteractResultState<List<ProductsItem>>>()
    val products: LiveData<InteractResultState<List<ProductsItem>>> = _products

    fun getListProductByFeature(page: Int, orderBy: OrderByFilter, order: OrderFilter) {
        viewModelScope.launch {
            val params = GetListProductsUseCase.Params(page, orderBy, order)
            getListProductsUseCase(params)
                .collectLatest { resultProductsItem ->
                    _products.postValue(resultProductsItem)
                }
        }
    }

    fun getListProductByCategory(
        categoryId: Int,
        page: Int,
        orderBy: OrderByFilter,
        order: OrderFilter
    ) {
        viewModelScope.launch {
            val params = GetListProductsByCategoryUseCase.Params(categoryId, page, orderBy, order)
            getListProductsByCategoryUseCase(params)
                .collectLatest { resultProductsItem ->
                    _products.postValue(resultProductsItem)
                }
        }
    }
}