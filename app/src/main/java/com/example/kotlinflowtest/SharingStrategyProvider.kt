package com.example.kotlinflowtest

import kotlinx.coroutines.flow.SharingStarted

data class SharingStrategyProvider(
    val lazily: SharingStarted = SharingStarted.Lazily,
    val eagerly: SharingStarted = SharingStarted.Eagerly,
    val whileSubscribed: SharingStarted = SharingStarted.WhileSubscribed()
)