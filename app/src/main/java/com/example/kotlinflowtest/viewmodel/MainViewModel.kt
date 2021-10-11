package com.example.kotlinflowtest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinflowtest.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainRepository: MainRepository
): ViewModel() {

    fun launchASuspendFunction() = viewModelScope.launch {
        mainRepository.repositorySuspendingFunction()
    }

    suspend fun suspendingFunction() {
        mainRepository.repositorySuspendingFunction()
    }

}