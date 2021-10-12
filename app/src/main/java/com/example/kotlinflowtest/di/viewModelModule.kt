package com.example.kotlinflowtest.di

import com.example.kotlinflowtest.CoroutineDispatcherProvider
import com.example.kotlinflowtest.SharingStrategyProvider
import com.example.kotlinflowtest.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get(), CoroutineDispatcherProvider(), SharingStrategyProvider()) }
}