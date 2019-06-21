package com.clockworkant.betabakers

import android.app.Application
import com.clockworkant.betabakers.di.repoModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BetaBakeryApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@BetaBakeryApp)
            modules(repoModule)
        }
    }
}