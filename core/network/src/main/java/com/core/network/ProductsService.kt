package com.core.network

import com.core.network.model.category.CategoriesItemDto
import com.core.network.model.details.ProductDetailsDto
import com.core.network.model.products.ProductsResponseItemDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsService {
    @GET("products")
    suspend fun getListProducts(
        @Query("page") page: Int,
        @Query("orderby") orderBy: String,
        @Query("order") order: String
    ): List<ProductsResponseItemDto>

    @GET("products")
    fun getListProductsByCategory(
        @Query("category") category: Int,
        @Query("page") page: Int,
        @Query("orderby") orderBy: String,
        @Query("order") order: String
    ): List<ProductsResponseItemDto>

    @GET("products/categories")
    fun getListCategories(
        @Query("page") page: Int,
    ): List<CategoriesItemDto>

    @GET("products")
    fun searchProducts(
        @Query("search") querySearch: String,
        @Query("page") page: Int,
        @Query("orderby") orderBy: String,
        @Query("order") order: String
    ): List<ProductsResponseItemDto>

    @GET("products/categories")
    fun searchCategories(
        @Query("search") querySearch: String,
        @Query("page") page: Int,
    ): List<CategoriesItemDto>


    @GET("products/{product_id}")
    fun getProductDetails(@Path("product_id") productId: Int): ProductDetailsDto
}