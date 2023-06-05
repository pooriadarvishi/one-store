package com.core.network

import com.core.network.model.category.Categories
import com.core.network.model.details.ProductDetails
import com.core.network.model.products.Products
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsService {
    @GET("products")
    suspend fun getListProducts(
        @Query("page") page: Int, @Query("orderby") orderBy: String
    ): Products

    @GET("products")
    suspend fun getListProductsByCategory(
        @Query("category") category: Int,
        @Query("page") page: Int,
        @Query("orderby") orderBy: String,
    ): Products

    @GET("products/categories")
    suspend fun getListCategories(
        @Query("page") page: Int,
    ): Categories

    @GET("products")
    suspend fun searchProducts(
        @Query("search") querySearch: String,
        @Query("page") page: Int,
        @Query("orderby") orderBy: String,
    ): Products

    @GET("products/categories")
    suspend fun searchCategories(
        @Query("search") querySearch: String,
        @Query("page") page: Int,
    ): Categories


    @GET("products/{product_id}")
    suspend fun getProductDetails(@Path("product_id") productId: Int): ProductDetails
}