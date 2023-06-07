package com.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.enums.common.OrderingFilters
import com.core.common.model.models.products.ProductsItem
import com.data.products.data.repository.ProductRepository
import com.example.common_main.result.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository, private val orderingFilters: OrderingFilters
) : ViewModel() {
    private val _lastedProducts = MutableLiveData<ResponseState<List<ProductsItem>>>()
    val lastedProducts: LiveData<ResponseState<List<ProductsItem>>> = _lastedProducts

    private val _popularityProducts = MutableLiveData<ResponseState<List<ProductsItem>>>()
    val popularityProducts: LiveData<ResponseState<List<ProductsItem>>> = _popularityProducts


    private val _bestProducts = MutableLiveData<ResponseState<List<ProductsItem>>>()
    val bestProducts: LiveData<ResponseState<List<ProductsItem>>> = _bestProducts


    init {
        getLastedProducts()
        getPopularityProducts()
        getBestProducts()
    }

    private fun getLastedProducts() {
        viewModelScope.launch {
            productRepository.getListProducts(
                1, orderingFilters.orderByPrice(), orderingFilters.orderDefault()
            ).collect { resultProductsItems ->
                _lastedProducts.postValue(resultProductsItems)
            }
        }
    }

    private fun getPopularityProducts() {
        viewModelScope.launch {
            productRepository.getListProducts(
                1, orderingFilters.orderByPopularity(), orderingFilters.orderDefault()
            ).collect { resultProductsItems ->
                _popularityProducts.postValue(resultProductsItems)
            }
        }
    }

    private fun getBestProducts() {
        viewModelScope.launch {
            productRepository.getListProducts(
                1, orderingFilters.orderByPopularity(), orderingFilters.orderDefault()
            ).collect { resultProductsItems ->
                _bestProducts.postValue(resultProductsItems)
            }
        }
    }


}