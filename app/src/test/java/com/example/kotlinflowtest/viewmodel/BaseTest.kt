package com.example.kotlinflowtest.viewmodel

import org.junit.Rule

open class BaseTest {

    @get:Rule
    val coroutineScope = CoroutineScopeRule()
}