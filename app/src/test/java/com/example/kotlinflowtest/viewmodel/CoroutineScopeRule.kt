package com.example.kotlinflowtest.viewmodel

import com.example.kotlinflowtest.CoroutineDispatcherProvider
import com.example.kotlinflowtest.SharingStrategyProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class CoroutineScopeRule(
    val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher(),
    var dispatcherProvider: CoroutineDispatcherProvider = CoroutineDispatcherProvider(),
    var sharingStrategyProvider: SharingStrategyProvider = SharingStrategyProvider()
): TestWatcher(), TestCoroutineScope by TestCoroutineScope(dispatcher) {

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
        dispatcherProvider = CoroutineDispatcherProvider(
            main = dispatcher,
            default = dispatcher,
            io = dispatcher
        )
        sharingStrategyProvider = SharingStrategyProvider(
            lazily = SharingStarted.Lazily,
            eagerly = SharingStarted.Lazily,
            whileSubscribed = SharingStarted.Lazily
        )
    }

    override fun finished(description: Description?) {
        super.finished(description)
        cleanupTestCoroutines()
        Dispatchers.resetMain()
    }

}