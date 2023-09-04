package com.joselaine.marvelapp.domain.usecase.base

import kotlinx.coroutines.flow.Flow

abstract class FlowUseCase<in P, R : Any> {
    suspend operator fun invoke(params: P): Flow<R> = createFlowObservable(params)
    protected abstract suspend fun createFlowObservable(params: P): Flow<R>
}