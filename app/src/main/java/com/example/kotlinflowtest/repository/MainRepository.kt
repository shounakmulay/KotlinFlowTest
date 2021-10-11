package com.example.kotlinflowtest.repository

import kotlinx.coroutines.flow.Flow

interface MainRepository {
    val counterFlow: Flow<Int>
    val squareFlow: Flow<Int>

    suspend fun repositorySuspendingFunction()
}
