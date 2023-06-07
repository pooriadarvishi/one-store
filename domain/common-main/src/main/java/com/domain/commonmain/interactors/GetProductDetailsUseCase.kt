package com.domain.commonmain.interactors

import com.core.common.model.models.details.ProductDetails
import com.data.products.data.repository.ProductRepository
import com.domain.commonmain.interact_result.InteractResult
import com.example.common_main.result.open
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductDetailsUseCase @Inject constructor(private val productRepository: ProductRepository) :
    InteractResult<GetProductDetailsUseCase.Params, ProductDetails>() {

    data class Params(val productId: Int)

    override suspend fun doWork(params: Params): Flow<ProductDetails> =
        productRepository.getProductDetails(params.productId).open()

}