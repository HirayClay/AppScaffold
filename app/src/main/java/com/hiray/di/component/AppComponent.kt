package com.hiray.di.component

import android.app.Application
import com.hiray.di.module.AppModule
import dagger.Component


@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(app: Application)
}