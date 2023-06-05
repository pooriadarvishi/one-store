package com.core.network

import com.core.network.model.category.Categories
import com.core.network.model.products.Products
import retrofit2.http.GET

interface ProductsService {
    @GET("v3/products")
    fun getListProducts() : Products

    @GET("v3/products/reviews/{category_id}")
    fun getListProductsByCategory() : Products

    @GET("v3/products/categories")
    fun getListCategories() : Categories

    @GET("v3/products")
    fun searchProducts() : Products


    //TODO
    @GET("v3/products")
    fun getProductDetails() : String
}