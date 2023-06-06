package com.core.common.model.models.category

data class CategoriesItem(
    val id: Int,
    val name: String?,
    val image: Image?,
    val description: String?,
    val count: Int?,
)