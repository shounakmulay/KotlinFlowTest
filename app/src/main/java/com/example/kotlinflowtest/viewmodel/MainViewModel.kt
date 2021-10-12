package com.example.kotlinflowtest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinflowtest.CoroutineDispatcherProvider
import com.example.kotlinflowtest.SharingStrategyProvider
import com.example.kotlinflowtest.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainRepository: MainRepository,
    private val dispatcherProvider: CoroutineDispatcherProvider,
    private val sharingStrategyProvider: SharingStrategyProvider
) : ViewModel() {


    val countFlow = mainRepository
        .count3Flow()
        .flowOn(dispatcherProvider.default)


    val doubleCountFlow = countFlow.map {
        it * 2
    }.flowOn(dispatcherProvider.io)

    val countWithDoubleFlow = countFlow.combine(doubleCountFlow) { count, double ->
        count to double
    }

    val doubleCountSharedFlow =
        doubleCountFlow.shareIn(viewModelScope, sharingStrategyProvider.eagerly)

    suspend fun getStateFlow(): MutableStateFlow<Int> {
        return MutableStateFlow(10)
    }


    fun launchASuspendFunction() = viewModelScope.launch(dispatcherProvider.main) {
        mainRepository.repositorySuspendingFunction()
        mainRepository.repositorySuspendingFunction()
    }

    suspend fun suspendingFunction() {
        mainRepository.repositorySuspendingFunction()
    }

}