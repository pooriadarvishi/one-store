package com.domain.commonmain.interact_result

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withTimeout
import java.util.concurrent.TimeUnit


interface InteractResult<in P, R> {
    operator fun invoke(params: P, timeoutMs: Long = defaultTimeoutMs): Flow<InteractResultState<R>> = flow {
        emit(InteractResultState.Loading)
        withTimeout(timeoutMs) {
            emit(InteractResultState.Success(doWork(params)))
        }
    }.catch { emit(InteractResultState.Error) }


    companion object {
        private val defaultTimeoutMs = TimeUnit.MINUTES.toMillis(5)
    }

    suspend fun doWork(params: P): R
}