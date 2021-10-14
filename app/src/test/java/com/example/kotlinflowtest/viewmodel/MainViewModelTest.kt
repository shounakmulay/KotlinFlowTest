package com.example.kotlinflowtest.viewmodel

import app.cash.turbine.test
import com.example.kotlinflowtest.repository.MainRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.times
import org.mockito.kotlin.*
import kotlin.time.ExperimentalTime


@ExperimentalTime
class MainViewModelTest : BaseTest() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var mainRepository: MainRepository

    @Before
    fun setUp() {
        mainRepository = mock()
        whenever(mainRepository.count3Flow()).doReturn(getCount3Flow())
        mainViewModel = MainViewModel(
            mainRepository,
            coroutineScope.dispatcherProvider,
            coroutineScope.sharingStrategyProvider
        )
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
        coroutineScope.dispatcher.runBlockingTest {

            val countFlow = mainViewModel.countWithDoubleFlow

            assertEquals(listOf(1 to 2, 2 to 4, 3 to 6), countFlow.toList())
        }

    @Test
    fun `When getStateFlow is called, it should emit values correctly`() = runBlocking {
        val stateFlow = mainViewModel.getStateFlow()

        stateFlow.test {
            val firstItem = awaitItem()
            assertEquals(10, firstItem)

            stateFlow.emit(20)
            val secondItem = awaitItem()
            assertEquals(20, secondItem)

            stateFlow.emit(20)
            expectNoEvents()
        }
    }

    @Test
    fun `When countWithDoubleSharedFlow is called, it should emit values correctly`() =
        coroutineScope.dispatcher.runBlockingTest {
            val sharedFlow = mainViewModel.doubleCountSharedFlow

            sharedFlow.test {
                val firstItem = awaitItem()
                assertEquals(2, firstItem)

                val secondItem = awaitItem()
                assertEquals(4, secondItem)

                val thirdItem = awaitItem()
                assertEquals(6, thirdItem)
            }
        }

    private fun getCount3Flow() = flow {
        (1..3).forEach {
            emit(it)
        }
    }
}