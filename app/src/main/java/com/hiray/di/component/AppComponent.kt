package com.hiray.di.component

import com.hiray.data.AppDataBase
import com.hiray.di.module.AppModule
import com.hiray.executor.AppExecutor
import dagger.Component


@Component(modules = [AppModule::class])
interface AppComponent {

    fun appDatabase():AppDataBase

    fun appExecutor():AppExecutor
}