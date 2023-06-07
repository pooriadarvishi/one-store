package com.data.category.data.repository

import com.core.common.model.models.category.CategoriesItem
import com.example.common_main.result.ResponseState
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getListCategories(
        page: Int,
    ): Flow<ResponseState<List<CategoriesItem>>>


    suspend fun searchCategories(
        querySearch: String,
        page: Int,
    ): Flow<ResponseState<List<CategoriesItem>>>
}