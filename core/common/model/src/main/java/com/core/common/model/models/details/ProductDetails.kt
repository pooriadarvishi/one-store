package com.core.common.model.models.details

data class ProductDetails(
    val id: Int,
    val name: String,
    val description: String?,
    val price: String?,
    val regularPrice: String?,
    val salePrice: String?,
    val averageRating: String?,
    val categories: List<Category>,
    val images: List<Image>,
    val ratingCount: Int?,
    val shortDescription: String?,
    val tags: List<Tag>,
    val relatedIds: List<Int>,
    val weight: String?,
    val dimensions: Dimensions?
)