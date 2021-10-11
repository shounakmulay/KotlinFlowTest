package com.example.kotlinflowtest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinflowtest.CoroutineDispatcherProvider
import com.example.kotlinflowtest.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainRepository: MainRepository,
    private val dispatcherProvider: CoroutineDispatcherProvider
): ViewModel() {

    fun launchASuspendFunction() = viewModelScope.launch(dispatcherProvider.main) {
        mainRepository.repositorySuspendingFunction()
        mainRepository.repositorySuspendingFunction()
    }

    suspend fun suspendingFunction() {
        mainRepository.repositorySuspendingFunction()
    }

}