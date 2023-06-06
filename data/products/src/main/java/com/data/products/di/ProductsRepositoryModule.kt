package com.data.products.di

import com.core.common.legacy.mappers.DetailsDtoToDetails
import com.core.common.legacy.mappers.ProductDtoToProduct
import com.core.network.ProductsService
import com.data.products.data.network.ProductNetworkDataSource
import com.data.products.data.network.ProductNetworkDataSourceImpl
import com.data.products.data.repository.ProductRepository
import com.data.products.data.repository.ProductRepositoryImpl
import com.example.common_main.network.Dispatcher
import com.example.common_main.network.KinDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ProductsRepositoryModule {

    @Provides
    @Singleton
    fun provideProductNetworkDataSource(
        productsService: ProductsService,
        productDtoToProduct: ProductDtoToProduct,
        detailsDtoToDetails: DetailsDtoToDetails,
        @Dispatcher(KinDispatchers.IO) ioDispatchers: CoroutineDispatcher,
        @Dispatcher(KinDispatchers.DEFAULT) defaultDispatchers: CoroutineDispatcher,
    ): ProductNetworkDataSource = ProductNetworkDataSourceImpl(
        productsService, productDtoToProduct, detailsDtoToDetails, ioDispatchers, defaultDispatchers
    )

    @Provides
    @Singleton
    fun provideProductRepository(productNetworkDataSource: ProductNetworkDataSource): ProductRepository =
        ProductRepositoryImpl(productNetworkDataSource)
}