package com.hiray.di.component

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.hiray.App
import com.hiray.mvvm.model.AppDataBase
import com.hiray.di.module.AppModule
import com.hiray.executor.AppExecutor
import com.hiray.mvvm.model.RestApi
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(app: App)

    fun appDatabase(): AppDataBase

    fun appExecutor(): AppExecutor

    fun app(): Application

    fun appContext(): Context

//    fun tslProvider():TslProvider

    fun gson(): Gson

    fun restApi(): RestApi

    fun sharePref(): SharedPreferences

}