package com.github.clockworkclyde.sweepyhelper

import android.app.Application
import com.github.clockworkclyde.sweepyhelper.di.appModule
import com.github.clockworkclyde.sweepyhelper.di.dataModule
import com.github.clockworkclyde.sweepyhelper.di.databaseModule
import com.github.clockworkclyde.sweepyhelper.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class SweepyHelperApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@SweepyHelperApp)
            modules(appModule, presentationModule, dataModule, databaseModule)
        }
    }
}