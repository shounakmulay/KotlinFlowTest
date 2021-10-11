package com.example.kotlinflowtest.repository

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MainRepositoryImpl: MainRepository {
    override val counterFlow = flow {
        emit(1)
        delay(1000)
    }

    override val squareFlow = counterFlow.map {
        it * it
    }

    override suspend fun repositorySuspendingFunction() {
        return
    }
}