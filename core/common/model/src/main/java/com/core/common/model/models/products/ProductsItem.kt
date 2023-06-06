package com.core.common.model.models.products

data class ProductsItem(
    val id: Int?,
    val averageRating: String?,
    val images: List<Image?>?,
    val name: String?,
    val price: String?,
    val regularPrice : String?,
    val salePrice : String?
)