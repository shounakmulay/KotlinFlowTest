package com.example.kotlinflowtest.viewmodel

import com.example.kotlinflowtest.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var mainRepository: MainRepository

    @Before
    fun setUp() {
        mainRepository = mock()
        mainViewModel = MainViewModel(mainRepository)
    }

    @Test
    fun `Given suspendingFunction is called, When no error occurs, Then repositorySuspendingFunction should be invoked successfully`() = runBlocking {
        mainViewModel.suspendingFunction()

        verify(mainRepository, times(1)).repositorySuspendingFunction()
    }
}