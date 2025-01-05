package com.williamfq.xhat

import android.app.Application
import androidx.multidex.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class XhatApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Configurar Timber para registro de errores
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
