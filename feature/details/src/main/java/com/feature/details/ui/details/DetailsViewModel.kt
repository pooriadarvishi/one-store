package com.feature.details.ui.details

import androidx.lifecycle.viewModelScope
import com.core.common.model.models.details.ProductDetails
import com.core.common.ui.ui.BaseViewModel
import com.domain.commonmain.interact_result.InteractResultState
import com.domain.commonmain.interactors.GetProductDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getProductDetails: GetProductDetailsUseCase,
) : BaseViewModel<InteractResultState<ProductDetails>>() {
    private var productId: Int = 0

    override var _data =
        MutableStateFlow<InteractResultState<ProductDetails>>(InteractResultState.Loading)

    operator fun invoke() {
        getProductDetails()
    }

    private fun getProductDetails() {
        viewModelScope.launch {
            getProductDetails(GetProductDetailsUseCase.Params(productId)).collect { ResultProductDetails ->
                _data.value = ResultProductDetails
            }
        }
    }


    fun setProductId(id: Int) {
        productId = id
    }


}