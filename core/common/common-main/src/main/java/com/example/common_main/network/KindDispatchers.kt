package com.example.common_main.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val niaDispatcher: KinDispatchers)

enum class KinDispatchers {
    Default,
    IO,
}