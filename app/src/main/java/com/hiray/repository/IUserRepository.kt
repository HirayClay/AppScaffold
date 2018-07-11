package com.hiray.repository

import com.hiray.mvvm.model.User
import com.hiray.repository.datasource.UserDataSource
import javax.inject.Inject

interface IUserRepository {

    fun getUser(userName: String): User

    fun deleteUser(userName: String)
    fun saveUser(userName: String, favoriteFruit: String)
}

open class UserRepository @Inject constructor(var userDataSource: UserDataSource) : IUserRepository {

    override fun saveUser(userName: String, favoriteFruit: String) {
        userDataSource.saveUser(User(userName, favoriteFruit))
    }

    override fun deleteUser(userName: String) {
        userDataSource.deleteUser(userName)
    }

    override fun getUser(userName: String): User {
        return userDataSource.getUser(userName)
    }

}