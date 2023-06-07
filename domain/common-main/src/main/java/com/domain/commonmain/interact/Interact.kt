package com.domain.commonmain.interact

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withTimeout
import java.util.concurrent.TimeUnit

interface Interact<in P> {
    operator fun invoke(params: P, timeoutMs: Long = defaultTimeoutMs): Flow<InteractState> = flow {
        withTimeout(timeoutMs) {
            emit(InteractState.Started)
            doWork(params)
            emit(InteractState.Success)
        }
    }.catch { emit(InteractState.Error(it)) }

    suspend fun doWork(params: P)

    companion object {
        private val defaultTimeoutMs = TimeUnit.MINUTES.toMillis(5)
    }

}