package com.example.kotlinflowtest.viewmodel

import com.example.kotlinflowtest.repository.MainRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.times
import org.mockito.kotlin.*


class MainViewModelTest : BaseTest() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var mainRepository: MainRepository

    @Before
    fun setUp() {
        mainRepository = mock()
        whenever(mainRepository.count3Flow()).doReturn(getCount3Flow())
        mainViewModel = MainViewModel(mainRepository, coroutineScope.dispatcherProvider)
    }

    @Test
    fun `Given suspendingFunction is called, When no error occurs, Then repositorySuspendingFunction should be invoked successfully`() =
        runBlocking {
            mainViewModel.suspendingFunction()

            verify(mainRepository, times(1)).repositorySuspendingFunction()
        }

    @Test
    fun `Given launchASuspendFunction is called, When no error occurs, Then repositorySuspendingFunction should be invoked successfully`() =
        runBlocking {
            mainViewModel.launchASuspendFunction()

            verify(mainRepository, times(2)).repositorySuspendingFunction()
        }

    @Test
    fun `Given no error occurs, When count3Flow is called, Then it should emit all values correctly`() =
        runBlocking {

            val countFlow = mainViewModel.countFlow

            assertEquals(listOf(1, 2, 3), countFlow.toList())
        }

    private fun getCount3Flow() = flow {
        (1..3).forEach {
            emit(it)
        }
    }
}