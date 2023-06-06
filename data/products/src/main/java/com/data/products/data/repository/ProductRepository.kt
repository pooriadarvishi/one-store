package com.data.products.data.repository

import com.core.common.enums.enums.OrderByFilter
import com.core.common.enums.enums.OrderFilter
import com.core.common.model.models.details.ProductDetails
import com.core.common.model.models.products.ProductsItem
import com.example.common_main.result.Result
import kotlinx.coroutines.flow.Flow


interface ProductRepository {
    suspend fun getListProducts(
        page: Int,
        orderBy: OrderByFilter,
        order: OrderFilter
    ): Flow<Result<List<ProductsItem>>>


    suspend fun getListProductsByCategory(
        category: Int,
        page: Int,
        orderBy: OrderByFilter,
        order: OrderFilter
    ): Flow<Result<List<ProductsItem>>>


    suspend fun searchProducts(
        querySearch: String,
        page: Int,
        orderBy: OrderByFilter,
        order: OrderFilter
    ): Flow<Result<List<ProductsItem>>>


    suspend fun getProductDetails(productId: Int): Flow<Result<ProductDetails>>

}