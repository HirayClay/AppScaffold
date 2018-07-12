package com.hiray

import android.app.Application
import com.hiray.di.component.AppComponent
import com.hiray.di.component.DaggerAppComponent
import com.hiray.di.module.AppModule

class App : Application() {

    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}