package com.hiray.di.module

import com.hiray.repository.IUserRepository
import com.hiray.repository.UserRepository
import com.hiray.repository.datasource.UserDataSource
import com.hiray.repository.datasource.UserDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class BindsModule {

    @Binds
    abstract fun bindUserRepo(userRepo:UserRepository):IUserRepository


    @Binds
    abstract fun bindUserDataSource(userDS:UserDataSourceImpl):UserDataSource
}