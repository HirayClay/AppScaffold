package com.hiray.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.hiray.BuildConfig
import com.hiray.mvvm.model.AppDataBase
import com.hiray.executor.AppExecutor
import com.hiray.mvvm.model.RestApi
import com.hiray.mvvm.model.RestApiHelper
import com.hiray.mvvm.viewmodel.NetWorkViewModel
import com.hiray.mvvm.viewmodel.UserViewModel
import com.hiray.tsl.TslProvider
import dagger.Module
import dagger.Provides
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class AppModule(var appContext: Application) {

    @Singleton
    @Provides
    fun provideApp(): Application {
        return appContext
    }

    @Singleton
    @Provides
    fun provideContext(): Context {
        return appContext
    }

    @Singleton
    @Provides
    fun tslProvider(): TslProvider {
        return TslProvider(appContext)
    }

    @Singleton
    @Provides
    fun sharePref(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(appContext)
    }

    @Singleton
    @Provides
    fun provideDatabase(): AppDataBase {
        return AppDataBase.getInstance(appContext)
    }

    @Singleton
    @Provides
    fun provideNetWorkViewModel(): NetWorkViewModel {
        return NetWorkViewModel(appContext)
    }

    @Singleton
    @Provides
    fun provideUserViewModel(): UserViewModel {
        return UserViewModel()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    fun provideAppExecutor(executor: ExecutorService): AppExecutor {
        return AppExecutor(executor)
    }

    @Singleton
    @Provides
    fun provideConcurrentExecutor(): ExecutorService {
        return Executors.newFixedThreadPool(BuildConfig.THREAD_POOL_SIZE)
    }

    @Singleton
    @Provides
    fun provideRestApiHelper(gson: Gson, tslProvider: TslProvider): RestApiHelper {
        return RestApiHelper(gson, tslProvider)
    }

    @Singleton
    @Provides
    fun provideRestApi(restAipHelper: RestApiHelper): RestApi {
        return restAipHelper.create()
    }
}