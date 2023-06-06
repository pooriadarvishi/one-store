package com.feature.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.enums.common.OrderingFilters
import com.core.common.model.models.products.ProductsItem
import com.data.products.data.repository.ProductRepository
import com.example.common_main.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val orderingFilters: OrderingFilters
) : ViewModel() {
    private val _data = MutableLiveData<Result<List<ProductsItem>>>()
    val data: LiveData<Result<List<ProductsItem>>> = _data


    fun getData() {
        viewModelScope.launch {
            productRepository.getListProducts(
                1,
                orderingFilters.orderByPrice(),
                orderingFilters.orderDefault()
            ).collect {
                when (it) {
                    is Result.Error -> Log.e("RESULT", "getData: ${it.exception?.message}")
                    Result.Loading -> Log.e("RESULT", "getData: loading")
                    is Result.Success -> Log.e("RESULT", "getData: ${it.data}")
                }
            }
        }
    }
}