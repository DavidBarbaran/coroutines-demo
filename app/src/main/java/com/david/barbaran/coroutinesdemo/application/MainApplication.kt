package com.david.barbaran.coroutinesdemo.application

import android.app.Application
import com.david.barbaran.coroutinesdemo.module.adapterModule
import com.david.barbaran.coroutinesdemo.module.presenterModule
import org.koin.android.ext.android.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(presenterModule, adapterModule))
    }
}