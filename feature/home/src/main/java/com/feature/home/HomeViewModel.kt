package com.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.enums.common.OrderingFilters
import com.core.common.model.models.products.ProductsItem
import com.data.products.data.repository.ProductRepositoryImpl
import com.example.common_main.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class HomeViewModel @Inject @Singleton constructor(
    private val productRepository: ProductRepositoryImpl,
    private val orderingFilters: OrderingFilters
) : ViewModel() {
    private val _data = MutableLiveData<Result<List<ProductsItem>>>()
    val data: LiveData<Result<List<ProductsItem>>> = _data


    fun getData() {
        viewModelScope.launch {
            productRepository.getListProducts(
                1,
                orderingFilters.orderByDefault(),
                orderingFilters.orderDefault()
            ).collect {
                _data.postValue(it)
            }
        }
    }

}