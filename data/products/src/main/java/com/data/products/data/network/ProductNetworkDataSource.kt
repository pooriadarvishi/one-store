package com.data.products.data.network

import com.core.common.model.models.details.ProductDetails
import com.core.common.model.models.products.ProductsItem
import kotlinx.coroutines.flow.Flow


interface ProductNetworkDataSource {
     fun getListProducts(
        page: Int,
        orderBy: String,
        order: String
    ): Flow<List<ProductsItem>>


     fun getListProductsByCategory(
        category: Int,
        page: Int,
        orderBy: String,
        order: String
    ): Flow<List<ProductsItem>>


     fun searchProducts(
        querySearch: String,
        page: Int,
        orderBy: String,
        order: String
    ): Flow<List<ProductsItem>>


    fun getProductDetails(productId: Int): Flow<ProductDetails>


}