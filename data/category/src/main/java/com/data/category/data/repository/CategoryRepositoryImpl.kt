package com.data.category.data.repository

import com.core.common.model.models.category.CategoriesItem
import com.data.category.data.network.CategoryNetworkDataSource
import com.example.common_main.result.ResponseState
import com.example.common_main.result.asResponseState
import kotlinx.coroutines.flow.Flow

class CategoryRepositoryImpl(private val categoryNetworkDataSource: CategoryNetworkDataSource) :
    CategoryRepository {
    override suspend fun getListCategories(page: Int): Flow<ResponseState<List<CategoriesItem>>> =
        categoryNetworkDataSource.getListCategories(page).asResponseState()

    override suspend fun searchCategories(
        querySearch: String, page: Int
    ): Flow<ResponseState<List<CategoriesItem>>> =
        categoryNetworkDataSource.getListCategories(page).asResponseState()
}