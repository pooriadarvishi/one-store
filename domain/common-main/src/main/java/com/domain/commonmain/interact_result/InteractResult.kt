package com.domain.commonmain.interact_result

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withTimeout
import java.util.concurrent.TimeUnit


abstract class InteractResult<P, Q> {
    operator fun invoke(
        params: P, timeoutMs: Long = defaultTimeoutMs
    ): Flow<InteractResultState<Q>> =

        doWork(params).map { q ->
            withTimeout(timeoutMs) {
                InteractResultState.Success(q) as InteractResultState<Q>
            }
        }.catch {
            Log.e("INTERACT", "Catch: ${it.message}")
            emit(InteractResultState.Error)
        }.onStart {
            Log.e("INTERACT", "Loading : StartLoading")
            emit(InteractResultState.Loading)
        }


    companion object {
        private val defaultTimeoutMs = TimeUnit.MINUTES.toMillis(5)
    }

    protected abstract fun doWork(params: P): Flow<Q>
}