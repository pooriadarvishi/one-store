package com.data.category.data.repository

import com.core.common.model.models.category.CategoriesItem
import com.data.category.data.network.CategoryNetworkDataSource
import com.example.common_main.result.Result
import com.example.common_main.result.asResult
import kotlinx.coroutines.flow.Flow

class CategoryRepositoryImpl(private val categoryNetworkDataSource: CategoryNetworkDataSource) :
    CategoryRepository {
    override suspend fun getListCategories(page: Int): Flow<Result<List<CategoriesItem>>> =
        categoryNetworkDataSource.getListCategories(page).asResult()

    override suspend fun searchCategories(
        querySearch: String, page: Int
    ): Flow<Result<List<CategoriesItem>>> =
        categoryNetworkDataSource.getListCategories(page).asResult()
}