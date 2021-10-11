package com.example.kotlinflowtest.di

import com.example.kotlinflowtest.repository.MainRepository
import com.example.kotlinflowtest.repository.MainRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<MainRepository> { MainRepositoryImpl() }
}