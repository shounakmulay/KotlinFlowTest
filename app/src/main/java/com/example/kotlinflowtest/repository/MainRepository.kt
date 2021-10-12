package com.example.kotlinflowtest.repository

import kotlinx.coroutines.flow.Flow


interface MainRepository {

    fun count3Flow(): Flow<Int>

    suspend fun repositorySuspendingFunction()
}
