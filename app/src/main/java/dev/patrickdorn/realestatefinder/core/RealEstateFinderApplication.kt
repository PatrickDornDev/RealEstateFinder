package dev.patrickdorn.realestatefinder.core

import android.annotation.SuppressLint
import android.app.Application
import dev.patrickdorn.realestatefinder.core.di.appModule
import dev.patrickdorn.realestatefinder.core.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RealEstateFinderApplication : Application() {

    @SuppressLint("VisibleForTests")
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@RealEstateFinderApplication)
            modules(appModule, dataModule)
        }
    }
}