package com.efisherylite.app

import android.app.Application
import com.efisherylite.app.external.extensions.debugMode
import com.efisherylite.app.koin.components.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Created by Yoga C. Pranata on 12/11/2020.
 * Android Engineer
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeKoin()
    }

    private fun initializeKoin() {
        startKoin {
            debugMode { androidLogger(Level.ERROR) }
            androidContext(this@MainApplication)
            modules(appComponent)
        }
    }

}