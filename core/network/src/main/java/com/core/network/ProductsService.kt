package com.core.network

import com.core.network.model.category.CategoriesDto
import com.core.network.model.details.ProductDetailsDto
import com.core.network.model.products.ProductsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsService {
    @GET("products")
    suspend fun getListProducts(
        @Query("page") page: Int, @Query("orderby") orderBy: String
    ): ProductsDto

    @GET("products")
    suspend fun getListProductsByCategory(
        @Query("category") category: Int,
        @Query("page") page: Int,
        @Query("orderby") orderBy: String,
    ): ProductsDto

    @GET("products/categories")
    suspend fun getListCategories(
        @Query("page") page: Int,
    ): CategoriesDto

    @GET("products")
    suspend fun searchProducts(
        @Query("search") querySearch: String,
        @Query("page") page: Int,
        @Query("orderby") orderBy: String,
    ): ProductsDto

    @GET("products/categories")
    suspend fun searchCategories(
        @Query("search") querySearch: String,
        @Query("page") page: Int,
    ): CategoriesDto


    @GET("products/{product_id}")
    suspend fun getProductDetails(@Path("product_id") productId: Int): ProductDetailsDto
}