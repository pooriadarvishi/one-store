package com.data.products.data.network

import com.core.common.model.models.products.ProductsItem
import kotlinx.coroutines.flow.Flow


interface ProductNetworkDataSource {
    suspend fun getListProducts(
        page: Int,
        orderBy: String,
        order: String
    ): Flow<List<ProductsItem>>


    suspend fun getListProductsByCategory(
        category: Int,
        page: Int,
        orderBy: String,
        order: String
    ): Flow<List<ProductsItem>>


    suspend fun searchProducts(
        querySearch: String,
        page: Int,
        orderBy: String,
        order: String
    ): Flow<List<ProductsItem>>

}