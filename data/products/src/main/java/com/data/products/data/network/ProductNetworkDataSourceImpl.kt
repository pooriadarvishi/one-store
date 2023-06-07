package com.data.products.data.network

import com.core.common.legacy.mappers.DetailsDtoToDetails
import com.core.common.legacy.mappers.ProductDtoToProduct
import com.core.common.legacy.mappers.map
import com.core.common.model.models.details.ProductDetails
import com.core.common.model.models.products.ProductsItem
import com.core.network.ProductsService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class ProductNetworkDataSourceImpl(
    private val productsService: ProductsService,
    private val productDtoToProduct: ProductDtoToProduct,
    private val detailsDtoToDetails: DetailsDtoToDetails,
    private val ioDispatchers: CoroutineDispatcher,
) : ProductNetworkDataSource {
    override suspend fun getListProducts(
        page: Int, orderBy: String, order: String
    ): Flow<List<ProductsItem>> = withContext(ioDispatchers) {
        flow {
            emit(productDtoToProduct.map(productsService.getListProducts(page, orderBy, order)))
        }
    }


    override suspend fun getListProductsByCategory(
        category: Int, page: Int, orderBy: String, order: String
    ): Flow<List<ProductsItem>> = withContext(ioDispatchers) {
        flow {
            emit(
                productDtoToProduct.map(
                    productsService.getListProductsByCategory(
                        category, page, orderBy, order
                    )
                )
            )
        }
    }


    override suspend fun searchProducts(
        querySearch: String, page: Int, orderBy: String, order: String
    ): Flow<List<ProductsItem>> = withContext(ioDispatchers) {
        flow {
            emit(
                productDtoToProduct.map(
                    productsService.searchProducts(
                        querySearch, page, orderBy, order
                    )
                )
            )
        }
    }

    override suspend fun getProductDetails(productId: Int): Flow<ProductDetails> =
        withContext(ioDispatchers) {
            flow {
                emit(detailsDtoToDetails.map(productsService.getProductDetails(productId)))
            }
        }
}