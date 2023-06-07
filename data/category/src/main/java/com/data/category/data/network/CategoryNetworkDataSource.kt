package com.data.category.data.network

import com.core.common.model.models.category.CategoriesItem
import kotlinx.coroutines.flow.Flow

interface CategoryNetworkDataSource {
    fun getListCategories(
        page: Int,
    ): Flow<List<CategoriesItem>>


    fun searchCategories(
        querySearch: String,
        page: Int,
    ): Flow<List<CategoriesItem>>
}