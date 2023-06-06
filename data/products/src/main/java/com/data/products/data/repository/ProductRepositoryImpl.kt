package com.data.products.data.repository

import com.core.common.enums.common.pureOrder
import com.core.common.enums.enums.OrderByFilter
import com.core.common.enums.enums.OrderFilter
import com.core.common.model.models.products.ProductsItem
import com.data.products.data.network.ProductNetworkDataSourceImpl
import com.example.common_main.result.Result
import com.example.common_main.result.asResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

class ProductRepositoryImpl(
    @Inject @Singleton private val productNetworkDataSourceImpl: ProductNetworkDataSourceImpl
) : ProductRepository {
    override suspend fun getListProducts(
        page: Int,
        orderBy: OrderByFilter,
        order: OrderFilter
    ): Flow<Result<List<ProductsItem>>> =
        productNetworkDataSourceImpl.getListProducts(page, pureOrder(orderBy), pureOrder(order))
            .asResult()

    override suspend fun getListProductsByCategory(
        category: Int,
        page: Int,
        orderBy: OrderByFilter,
        order: OrderFilter
    ): Flow<Result<List<ProductsItem>>> =
        productNetworkDataSourceImpl.getListProductsByCategory(
            category, page,
            pureOrder(orderBy),
            pureOrder(order)
        ).asResult()

    override suspend fun searchProducts(
        querySearch: String,
        page: Int,
        orderBy: OrderByFilter,
        order: OrderFilter
    ): Flow<Result<List<ProductsItem>>> = productNetworkDataSourceImpl.searchProducts(
        querySearch, page,
        pureOrder(orderBy),
        pureOrder(order)
    ).asResult()
}