package com.data.category.di

import com.core.common.legacy.mappers.CategoriesDtoToCategories
import com.core.network.ProductsService
import com.data.category.data.network.CategoryNetworkDataSource
import com.data.category.data.network.CategoryNetworkDataSourceImpl
import com.data.category.data.repository.CategoryRepository
import com.data.category.data.repository.CategoryRepositoryImpl
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
object CategoryRepositoryModule {

    @Provides
    @Singleton
    fun provideCategoryNetworkDataSource(
        productsService: ProductsService,
        categoriesDtoToCategories: CategoriesDtoToCategories,
        @Dispatcher(KinDispatchers.IO) ioDispatchers: CoroutineDispatcher,
    ): CategoryNetworkDataSource = CategoryNetworkDataSourceImpl(
        productsService,
        categoriesDtoToCategories,
        ioDispatchers
    )


    @Provides
    @Singleton
    fun provideCategoryRepository(categoryNetworkDataSource: CategoryNetworkDataSource): CategoryRepository =
        CategoryRepositoryImpl(categoryNetworkDataSource)

}