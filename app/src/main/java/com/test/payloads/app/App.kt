package com.test.payloads.app

import android.app.Application
import com.test.payloads.di.postModule
import org.koin.core.context.startKoin

class App:Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                postModule()
            )
        }
    }
}