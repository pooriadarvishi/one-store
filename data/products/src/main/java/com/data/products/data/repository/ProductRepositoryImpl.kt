package com.data.products.data.repository

import com.core.common.enums.common.asString
import com.core.common.enums.enums.OrderByFilter
import com.core.common.enums.enums.OrderFilter
import com.core.common.model.models.details.ProductDetails
import com.core.common.model.models.products.ProductsItem
import com.data.products.data.network.ProductNetworkDataSource
import com.example.common_main.result.ResponseState
import com.example.common_main.result.asResponseState
import kotlinx.coroutines.flow.Flow

class ProductRepositoryImpl(
    private val productNetworkDataSource: ProductNetworkDataSource
) : ProductRepository {
    override fun getListProducts(
        page: Int,
        orderBy: OrderByFilter,
        order: OrderFilter
    ): Flow<ResponseState<List<ProductsItem>>> =
        productNetworkDataSource.getListProducts(page, orderBy.asString(), order.asString())
            .asResponseState()

    override fun getListProductsByCategory(
        category: Int,
        page: Int,
        orderBy: OrderByFilter,
        order: OrderFilter
    ): Flow<ResponseState<List<ProductsItem>>> =
        productNetworkDataSource.getListProductsByCategory(
            category, page,
            orderBy.asString(),
            order.asString()
        ).asResponseState()

    override fun searchProducts(
        querySearch: String,
        page: Int,
        orderBy: OrderByFilter,
        order: OrderFilter
    ): Flow<ResponseState<List<ProductsItem>>> = productNetworkDataSource.searchProducts(
        querySearch, page,
        orderBy.asString(),
        order.asString()
    ).asResponseState()

    override fun getProductDetails(productId: Int): Flow<ResponseState<ProductDetails>> =
        productNetworkDataSource.getProductDetails(productId).asResponseState()
}