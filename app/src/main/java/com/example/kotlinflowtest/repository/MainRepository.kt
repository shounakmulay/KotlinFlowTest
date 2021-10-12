package com.example.kotlinflowtest.repository


interface MainRepository {

    suspend fun repositorySuspendingFunction()
}
