package com.hiray.di.module

import android.app.Activity
import android.content.Context
import android.support.annotation.Nullable
import com.hiray.mvvm.model.AppDataBase
import com.hiray.mvvm.model.entity.UserDao
import com.hiray.repository.IUserRepository
import com.hiray.repository.UserRepository
import com.hiray.repository.datasource.UserDataSource
import com.hiray.repository.datasource.UserDataSourceImpl
import dagger.Module
import dagger.Provides


/**
 * 用户信息模块，只负责用户信息获取，授权，登录，注册等和用户信息相关的功能
 */
@Module
class AccountModule {
    @Nullable
    var activity: Activity? = null


    constructor()
    constructor(activity: Activity?) {
        this.activity = activity
    }

    @Nullable
    @Provides
    fun provideActivity(): Activity? {
        return activity
    }

    @Provides
    fun provideUserDao(appDatabase: AppDataBase): UserDao {
        return appDatabase.userDao()
    }

    //use Binds instead of Provides
//    @Provides
//    fun provideUserDataSource(userDataSource: UserDataSourceImpl): UserDataSource {
//        return userDataSource
//    }

//    @Provides
//    fun provideUserRepo(userDataSource: UserDataSource): IUserRepository {
//        return UserRepository(userDataSource)
//    }

}