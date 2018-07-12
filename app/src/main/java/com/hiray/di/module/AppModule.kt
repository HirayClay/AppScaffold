package com.hiray.di.module

import android.app.Application
import com.hiray.BuildConfig
import com.hiray.data.AppDataBase
import com.hiray.di.ActivityScope
import com.hiray.di.Concurrent
import com.hiray.di.Sequential
import com.hiray.executor.AppExecutor
import com.hiray.mvvm.model.UserDao
import com.hiray.mvvm.model.UserDao_Impl
import com.hiray.repository.IUserRepository
import com.hiray.repository.UserRepository
import com.hiray.repository.datasource.UserDataSource
import com.hiray.repository.datasource.UserDataSourceImpl
import dagger.Module
import dagger.Provides
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class AppModule(var appContext: Application) {


    @Provides
    fun provideAppExecutor(executor: ExecutorService): AppExecutor {
        return AppExecutor(executor)
    }

    @Provides
    fun provideDatabase(): AppDataBase {
        return AppDataBase.getInstance(appContext)
    }


    @Provides
    fun provideConcurrentExecutor(): ExecutorService {
        return Executors.newFixedThreadPool(BuildConfig.THREAD_POOL_SIZE)
    }
}