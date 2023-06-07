package com.data.category.data.network

import com.core.common.legacy.mappers.CategoriesDtoToCategories
import com.core.common.legacy.mappers.map
import com.core.common.model.models.category.CategoriesItem
import com.core.network.ProductsService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class CategoryNetworkDataSourceImpl(
    private val productsService: ProductsService,
    private val categoriesDtoToCategories: CategoriesDtoToCategories,
    private val ioDispatchers: CoroutineDispatcher,
) : CategoryNetworkDataSource {
    override suspend fun getListCategories(page: Int): Flow<List<CategoriesItem>> =
        withContext(ioDispatchers) {
            flow {
                emit(categoriesDtoToCategories.map(productsService.getListCategories(page)))
            }
        }

    override suspend fun searchCategories(
        querySearch: String, page: Int
    ): Flow<List<CategoriesItem>> = withContext(ioDispatchers) {
        flow {
            emit(categoriesDtoToCategories.map(productsService.searchCategories(querySearch, page)))
        }
    }

}