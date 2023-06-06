package com.data.products.data.network

import com.core.common.legacy.mappers.ProductDtoToProduct
import com.core.common.legacy.mappers.map
import com.core.common.model.models.products.ProductsItem
import com.core.network.ProductsService
import com.example.common_main.network.Dispatcher
import com.example.common_main.network.KinDispatchers
import kotlinx.coroutines.CoroutineDispatcher
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
    ): List<ProductsItem> = withContext(ioDispatchers) {
        productDtoToProduct.map(productsService.getListProducts(page, orderBy, order))
    }


    override suspend fun getListProductsByCategory(
        category: Int, page: Int, orderBy: String, order: String
    ): List<ProductsItem> = withContext(ioDispatchers) {
        productDtoToProduct.map(
            productsService.getListProductsByCategory(
                category, page, orderBy, order
            )
        )
    }

    override suspend fun searchProducts(
        querySearch: String, page: Int, orderBy: String, order: String
    ): List<ProductsItem> = withContext(ioDispatchers) {
        productDtoToProduct.map(
            productsService.searchProducts(
                querySearch, page, orderBy, order
            )
        )
    }
}