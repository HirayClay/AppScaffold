package com.hiray.di.module

import android.app.Application
import com.hiray.data.AppDataBase
import com.hiray.mvvm.model.UserDao
import com.hiray.mvvm.model.UserDao_Impl
import com.hiray.repository.IUserRepository
import com.hiray.repository.UserRepository
import com.hiray.repository.datasource.UserDataSource
import com.hiray.repository.datasource.UserDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(var appContext: Application) {


    @Provides
    fun provideDatabase(): AppDataBase {
        return AppDataBase.getInstance(appContext)
    }

    @Provides
    fun provideUserDataSource(userDataSource: UserDataSourceImpl): UserDataSource {
        return userDataSource
    }

    @Provides
    fun provideUserDao(appDatabase: AppDataBase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    fun provideUserRepo(userDataSource: UserDataSource): IUserRepository {
        return UserRepository(userDataSource)
    }

}