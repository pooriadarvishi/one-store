package com.domain.commonmain.interact_result

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withTimeout
import java.util.concurrent.TimeUnit


abstract class InteractResult<P, Q> {
    suspend operator fun invoke(
        params: P,
        timeoutMs: Long = defaultTimeoutMs
    ): Flow<InteractResultState<Q>> =
        withTimeout(timeoutMs) {
            doWork(params).map { q ->
                InteractResultState.Success(q)
            }.catch { InteractResultState.Error }
        }.onStart { InteractResultState.Loading }


    companion object {
        private val defaultTimeoutMs = TimeUnit.MINUTES.toMillis(5)
    }

    protected abstract suspend fun doWork(params: P): Flow<Q>
}