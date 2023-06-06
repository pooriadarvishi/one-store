package com.data.products.data.network

import com.core.network.model.products.ProductsResponseItemDto

interface ProductNetworkDataSource {
    suspend fun getListProducts(
        page: Int,
        orderBy: String,
        order: String
    ): List<ProductsResponseItemDto>


    suspend fun getListProductsByCategory(
        category: Int,
        page: Int,
        orderBy: String,
        order: String
    ): List<ProductsResponseItemDto>


    suspend fun searchProducts(
        querySearch: String,
        page: Int,
        orderBy: String,
        order: String
    ): List<ProductsResponseItemDto>

}