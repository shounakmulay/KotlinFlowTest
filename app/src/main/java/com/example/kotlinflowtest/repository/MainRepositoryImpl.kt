package com.example.kotlinflowtest.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepositoryImpl: MainRepository {

    override fun count3Flow(): Flow<Int> = flow {
        (0..3).forEach {
            emit(it)
        }
    }

    override suspend fun repositorySuspendingFunction() {
        return
    }
}