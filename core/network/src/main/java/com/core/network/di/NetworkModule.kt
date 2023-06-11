package com.core.network.di

import com.core.network.ProductsService
import com.core.network.util.consts.Service
import com.core.network.util.provideApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideInterceptor(
    ): Interceptor = Interceptor { chain ->
        val url = chain.request()
            .url
            .newBuilder()
            .addQueryParameter(Service.CONSUMER_KEY, Service.CONSUMER_KEY_VALUE)
            .addQueryParameter(Service.CONSUMER_SECRET, Service.CONSUMER_SECRET_VALUE)
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
        .readTimeout(3,TimeUnit.MINUTES)
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(
        gsonConverter: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit
        .Builder()
        .baseUrl(Service.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(gsonConverter)
        .build()

    @Provides
    @Singleton
    fun provideProductService(retrofit: Retrofit): ProductsService = provideApiService(retrofit)
}