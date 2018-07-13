package com.hiray.di.module

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.hiray.App
import com.hiray.BuildConfig
import com.hiray.mvvm.model.AppDataBase
import com.hiray.executor.AppExecutor
import com.hiray.mvvm.model.RestApi
import com.hiray.mvvm.model.RestApiHelper
import dagger.Binds
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
    fun provideGson(): Gson {
        return Gson()
    }

    // 没有依赖任何 对象，可以不需要scope修饰
    @Singleton
    @Provides
    fun provideAppExecutor(executor: ExecutorService): AppExecutor {
        return AppExecutor(executor)
    }

    @Singleton
    @Provides
    fun provideDatabase(): AppDataBase {
        return AppDataBase.getInstance(appContext)
    }

    @Singleton
    @Provides
    fun provideConcurrentExecutor(): ExecutorService {
        return Executors.newFixedThreadPool(BuildConfig.THREAD_POOL_SIZE)
    }


    @Singleton
    @Provides
    fun provideRestApiHelper(): RestApiHelper {
        return RestApiHelper()
    }

    @Singleton
    @Provides
    fun provideRestApi(restAipHelper: RestApiHelper): RestApi {
        return restAipHelper.create()
    }
}