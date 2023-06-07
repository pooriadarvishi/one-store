package com.feature.details.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.model.models.details.ProductDetails
import com.domain.commonmain.interact_result.InteractResultState
import com.domain.commonmain.interactors.GetProductDetailsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val getProductDetails: GetProductDetailsUseCase,
) : ViewModel() {

    private val _productDetails = MutableLiveData<InteractResultState<ProductDetails>>()
    val productDetails: LiveData<InteractResultState<ProductDetails>> = _productDetails

    private var productId: Int = 0
    private fun getProductDetails() {
        viewModelScope.launch {
            val params = GetProductDetailsUseCase.Params(1)
            getProductDetails(params).collect { ResultProductDetails ->
                _productDetails.postValue(ResultProductDetails)
            }
        }
    }
}