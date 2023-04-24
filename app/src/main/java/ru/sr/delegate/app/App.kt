package ru.sr.delegate.app

import android.app.Application
import org.koin.core.context.startKoin
import ru.sr.delegate.di.postModule

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