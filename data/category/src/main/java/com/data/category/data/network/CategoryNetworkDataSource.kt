package com.data.category.data.network

import com.core.common.model.models.category.CategoriesItem
import kotlinx.coroutines.flow.Flow

interface CategoryNetworkDataSource {
    suspend fun getListCategories(
        page: Int,
    ): Flow<List<CategoriesItem>>


    suspend fun searchCategories(
        querySearch: String,
        page: Int,
    ): Flow<List<CategoriesItem>>
}