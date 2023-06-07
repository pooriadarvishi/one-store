package com.feature.products.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.enums.enums.OrderByFilter
import com.core.common.enums.enums.OrderFilter
import com.core.common.model.models.products.ProductsItem
import com.data.products.data.repository.ProductRepository
import com.example.common_main.result.ResponseState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductsViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {
    private val _products = MutableLiveData<ResponseState<List<ProductsItem>>>()
    val products: LiveData<ResponseState<List<ProductsItem>>> = _products

    fun getListProductByFeature(page: Int, orderBy: OrderByFilter, order: OrderFilter) {
        viewModelScope.launch {
            productRepository.getListProducts(page, orderBy, order)
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
            productRepository.getListProductsByCategory(categoryId, page, orderBy, order)
                .collectLatest { resultProductsItem ->
                    _products.postValue(resultProductsItem)
                }
        }
    }
}