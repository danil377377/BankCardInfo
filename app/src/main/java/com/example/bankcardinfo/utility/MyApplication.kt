package com.example.bankcardinfo.utility

import android.app.Application
import com.example.bankcardinfo.di.dataModule
import com.example.bankcardinfo.di.interactorModule
import com.example.bankcardinfo.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.GlobalContext.startKoin

class MyApplication : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(dataModule, interactorModule, viewModelModule)
        }
    }
}