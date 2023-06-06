package com.core.network.di

import com.core.network.ProductsService
import com.core.network.util.consts.Service
import com.core.network.util.provideApiService
import com.core.network.util.qualifier.BaseUrl
import com.core.network.util.qualifier.ConsumerKey
import com.core.network.util.qualifier.ConsumerSecret
import com.core.network.util.qualifier.PerPage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    @BaseUrl
    fun provideBaseUrl(): String = Service.BASE_URL

    @Provides
    @Singleton
    @ConsumerKey
    fun provideConsumerKey(): String = Service.CONSUMER_KEY_VALUE

    @Provides
    @Singleton
    @ConsumerSecret
    fun provideConsumerSecret(): String = Service.CONSUMER_SECRET_VALUE


    @Provides
    @Singleton
    @PerPage
    fun providePerPage(): String = Service.PER_PAGE_VAlUE


    @Provides
    @Singleton
    fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideInterceptor(
        @ConsumerKey consumerKey: String,
        @ConsumerSecret consumerSecret: String,
        @PerPage perpage: String,
    ): Interceptor = Interceptor { chain ->
        val url = chain.request()
            .url
            .newBuilder()
            .addQueryParameter(Service.CONSUMER_KEY, consumerKey)
            .addQueryParameter(Service.CONSUMER_SECRET, consumerSecret)
            .addQueryParameter(Service.PER_PAGE, perpage)
            .build()
        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()
        chain.proceed(request)
    }


    @Provides
    @Singleton
    fun provideOkHttpLog(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(interceptor)
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(
        @BaseUrl baseUrl: String,
        gsonConverter: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit
        .Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(gsonConverter)
        .build()

    @Provides
    @Singleton
    fun provideProductService(retrofit: Retrofit): ProductsService = provideApiService(retrofit)
}