package com.cerberus.galtwallet.application

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class ApplicationService<R, T>(serviceId: String) {
    private var TAG = "ApplicationService-${serviceId}"

    protected abstract suspend fun action(request: R): T

    operator fun invoke(request: R): Flow<T> = flow {
        emit(action(request = request))
    }
}
