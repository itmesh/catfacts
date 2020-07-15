package com.android.catfacts

import android.app.Application
import com.android.catfacts.di.networkModule
import com.android.catfacts.di.repositoryModule
import com.android.catfacts.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            modules(networkModule, repositoryModule, viewModelModule)
        }
    }
}