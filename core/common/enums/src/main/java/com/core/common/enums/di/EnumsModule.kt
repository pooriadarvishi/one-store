package com.core.common.enums.di

import com.core.common.enums.common.OrderingFilters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object EnumsModule {

    @Provides
    @Singleton
    fun provideOrderingFilters() = OrderingFilters()
}