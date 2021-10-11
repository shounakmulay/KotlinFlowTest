package com.example.kotlinflowtest

import android.app.Application
import com.example.kotlinflowtest.di.repositoryModule
import com.example.kotlinflowtest.di.viewModelModule
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class KotlinFlowTestApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            modules(
                listOf(
                    repositoryModule,
                    viewModelModule
                )
            )
        }

    }
}