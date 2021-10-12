package com.example.kotlinflowtest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinflowtest.CoroutineDispatcherProvider
import com.example.kotlinflowtest.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainRepository: MainRepository,
    private val dispatcherProvider: CoroutineDispatcherProvider
) : ViewModel() {

    val countFlow = mainRepository.count3Flow()

    fun launchASuspendFunction() = viewModelScope.launch(dispatcherProvider.main) {
        mainRepository.repositorySuspendingFunction()
        mainRepository.repositorySuspendingFunction()
    }

    suspend fun suspendingFunction() {
        mainRepository.repositorySuspendingFunction()
    }

}