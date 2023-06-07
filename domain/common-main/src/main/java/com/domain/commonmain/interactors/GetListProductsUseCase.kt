package com.domain.commonmain.interactors

import com.core.common.enums.enums.OrderByFilter
import com.core.common.enums.enums.OrderFilter
import com.core.common.model.models.products.ProductsItem
import com.data.products.data.repository.ProductRepository
import com.domain.commonmain.interact_result.InteractResult
import com.example.common_main.result.open
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListProductsUseCase @Inject constructor(private val productRepository: ProductRepository) :
    InteractResult<GetListProductsUseCase.Params, List<ProductsItem>>() {


    data class Params(
        val page: Int,
        val orderBy: OrderByFilter,
        val order: OrderFilter
    )

    override suspend fun doWork(params: Params): Flow<List<ProductsItem>> =
        productRepository.getListProducts(params.page, params.orderBy, params.order).open()

}