package com.core.network.util

import retrofit2.Retrofit

inline fun <reified T>provideApiService(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}