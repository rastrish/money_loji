package com.zerone.moneyloji

import android.app.Application
import com.zerone.moneyloji.di.koinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MoneyLojiApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MoneyLojiApplication)
            modules(koinModule)
        }

    }
}