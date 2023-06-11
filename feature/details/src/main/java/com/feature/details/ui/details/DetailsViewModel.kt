package com.feature.details.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.model.models.details.ProductDetails
import com.domain.commonmain.interact_result.InteractResultState
import com.domain.commonmain.interactors.GetProductDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getProductDetails: GetProductDetailsUseCase,
) : ViewModel() {
    private var productId: Int = 0

    private val _productDetails =
        MutableStateFlow<InteractResultState<ProductDetails>>(InteractResultState.Loading)
    val productDetails: StateFlow<InteractResultState<ProductDetails>> = _productDetails

    operator fun invoke() {
        getProductDetails()
    }

    private fun getProductDetails() {
        viewModelScope.launch {
            getProductDetails(GetProductDetailsUseCase.Params(productId)).collect { ResultProductDetails ->
                _productDetails.value = ResultProductDetails
            }
        }
    }

    fun setProductId(id: Int) {
        productId = id
    }


}