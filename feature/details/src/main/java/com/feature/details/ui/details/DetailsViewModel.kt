package com.feature.details.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.model.models.details.ProductDetails
import com.data.products.data.repository.ProductRepository
import com.example.common_main.result.ResponseState
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val productRepository: ProductRepository,
) : ViewModel() {

    private val _productDetails = MutableLiveData<ResponseState<ProductDetails>>()
    val productDetails: LiveData<ResponseState<ProductDetails>> = _productDetails

    private var productId: Int = 0
    private fun getProductDetails() {
        viewModelScope.launch {
            productRepository.getProductDetails(productId).collect { ResultProductDetails ->
                _productDetails.postValue(ResultProductDetails)
            }
        }
    }
}