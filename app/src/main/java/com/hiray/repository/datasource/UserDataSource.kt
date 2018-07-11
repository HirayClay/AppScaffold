package com.hiray.repository.datasource

import com.hiray.mvvm.model.User
import com.hiray.mvvm.model.UserDao
import javax.inject.Inject

interface UserDataSource {

    fun getUser(userName: String): User

    fun deleteUser(userName: String)

    fun saveUser(user: User)
}

/**
 * for now only local datasource is implemented,so just query from userDao
 */
open class UserDataSourceImpl @Inject constructor(var userDao: UserDao) : UserDataSource {

    override fun saveUser(user: User) {
        userDao.addUser(user)
    }


    override fun deleteUser(userName: String) {
        //todo delete it in remote
        userDao.deleteUser(userName)
    }


    override fun getUser(userName: String): User {
        return userDao.getUserByName(userName)
    }


}