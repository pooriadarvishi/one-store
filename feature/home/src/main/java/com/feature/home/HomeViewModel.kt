package com.feature.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.enums.common.OrderingFilters
import com.core.common.model.models.products.ProductsItem
import com.domain.commonmain.interact_result.InteractResultState
import com.domain.commonmain.interactors.GetListProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getListProductsUseCase: GetListProductsUseCase,
    private val orderingFilters: OrderingFilters
) : ViewModel() {
    private val _lastedProducts = MutableLiveData<InteractResultState<List<ProductsItem>>>()

    private val _popularityProducts = MutableLiveData<InteractResultState<List<ProductsItem>>>()


    private val _bestProducts = MutableLiveData<InteractResultState<List<ProductsItem>>>()


    val allProducts = listOf(_lastedProducts, _popularityProducts, _bestProducts)


    init {
        getLastedProducts()
        getPopularityProducts()
        getBestProducts()
    }


    private fun getLastedProducts() {
        viewModelScope.launch {
            val params = GetListProductsUseCase.Params(
                1, orderingFilters.orderById(), orderingFilters.orderDefault()
            )
            getListProductsUseCase(
                params
            ).collect { resultProductsItems ->
                _lastedProducts.postValue(resultProductsItems)
            }
        }
    }

    private fun getPopularityProducts() {
        viewModelScope.launch {
            val params = GetListProductsUseCase.Params(
                1, orderingFilters.orderByPopularity(), orderingFilters.orderDefault()
            )
            getListProductsUseCase(
                params
            ).collect { resultProductsItems ->
                _popularityProducts.postValue(resultProductsItems)
            }
        }
    }

    private fun getBestProducts() {
        viewModelScope.launch {
            val params = GetListProductsUseCase.Params(
                1, orderingFilters.orderByRating(), orderingFilters.orderDefault()
            )
            getListProductsUseCase(
                params
            ).collect { resultProductsItems ->
                _bestProducts.postValue(resultProductsItems)
            }
        }
    }


}