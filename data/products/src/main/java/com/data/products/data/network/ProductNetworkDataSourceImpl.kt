package com.data.products.data.network

import com.core.common.legacy.mappers.ProductDtoToProduct
import com.core.common.legacy.mappers.map
import com.core.common.model.models.products.ProductsItem
import com.core.network.ProductsService
import com.example.common_main.network.Dispatcher
import com.example.common_main.network.KinDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

class ProductNetworkDataSourceImpl(
    @Inject @Singleton private val productsService: ProductsService,
    @Inject @Singleton private val productDtoToProduct: ProductDtoToProduct,
    @Inject @Singleton @Dispatcher(KinDispatchers.IO) private val ioDispatchers: CoroutineDispatcher,
    @Inject @Singleton @Dispatcher(KinDispatchers.Default) private val defaultDispatchers: CoroutineDispatcher,
) : ProductNetworkDataSource {
    override suspend fun getListProducts(
        page: Int, orderBy: String, order: String
    ): Flow<List<ProductsItem>> = withContext(ioDispatchers) {
        productsService.getListProducts(page, orderBy, order).map { productsItemsDto ->
            productDtoToProduct.map(productsItemsDto)
        }
    }


    override suspend fun getListProductsByCategory(
        category: Int, page: Int, orderBy: String, order: String
    ): Flow<List<ProductsItem>> = withContext(ioDispatchers) {
        productsService.getListProductsByCategory(
            category, page, orderBy, order
        ).map { productsItemsDto ->
            productDtoToProduct.map(productsItemsDto)
        }
    }

    override suspend fun searchProducts(
        querySearch: String, page: Int, orderBy: String, order: String
    ): Flow<List<ProductsItem>> = withContext(ioDispatchers) {
        productsService.searchProducts(
            querySearch, page, orderBy, order
        ).map { productsItemsDto ->
            productDtoToProduct.map(productsItemsDto)
        }
    }
}