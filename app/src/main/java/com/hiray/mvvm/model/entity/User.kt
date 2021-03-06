package com.hiray.mvvm.model.entity

import android.arch.persistence.room.*

@Entity(tableName = "User")
data class User(
        @PrimaryKey
        var userName: String,
        var password: String) {
}

@Dao
interface UserDao {

    @Query("SELECT * FROM User WHERE userName = :userName")
    fun getUserByName(userName: String): User

    @Query("SELECT * FROM User")
    fun getUsers(): List<User>

    @Query("DELETE FROM User")
    fun deleteAll()

    @Query("DELETE FROM User WHERE userName = :userName")
    fun deleteUser(userName: String)

    @Update
    fun updateUser(user: User)

    @Update
    fun updateUsers(users: List<User>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(users: List<User>)
}