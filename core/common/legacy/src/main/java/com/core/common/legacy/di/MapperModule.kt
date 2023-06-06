package com.core.common.legacy.di

import com.core.common.legacy.mappers.CategoriesDtoToCategories
import com.core.common.legacy.mappers.DetailsDtoToDetails
import com.core.common.legacy.mappers.ProductDtoToProduct
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class MapperModule {

    @Provides
    @Singleton
    fun provideDetailsDtoToDetails(): DetailsDtoToDetails = DetailsDtoToDetails()


    @Provides
    @Singleton
    fun provideProductDtoToProduct(): ProductDtoToProduct = ProductDtoToProduct()


    @Provides
    @Singleton
    fun provideCategoriesDtoToCategories(): CategoriesDtoToCategories = CategoriesDtoToCategories()
}