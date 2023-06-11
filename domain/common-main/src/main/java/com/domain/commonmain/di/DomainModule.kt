package com.domain.commonmain.di

import com.data.category.data.repository.CategoryRepository
import com.data.products.data.repository.ProductRepository
import com.domain.commonmain.interactors.GetListCategoriesUseCase
import com.domain.commonmain.interactors.GetListProductsByCategoryUseCase
import com.domain.commonmain.interactors.GetListProductsUseCase
import com.domain.commonmain.interactors.GetProductDetailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DomainModule {


    @Provides
    fun provideGetListCategoriesUseCase(categoryRepository: CategoryRepository): GetListCategoriesUseCase =
        GetListCategoriesUseCase(categoryRepository)

    @Provides
    fun provideGetListProductsByCategoryUseCase(productRepository: ProductRepository): GetListProductsByCategoryUseCase =
        GetListProductsByCategoryUseCase(productRepository)

    @Provides
    fun provideGetListProductsUseCase(productRepository: ProductRepository): GetListProductsUseCase =
        GetListProductsUseCase(productRepository)

    @Provides
    fun provideGetProductDetailsUseCase(productRepository: ProductRepository): GetProductDetailsUseCase =
        GetProductDetailsUseCase(productRepository)

}