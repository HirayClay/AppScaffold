package com.hiray.mvvm.model

import android.arch.persistence.room.*
import retrofit2.http.DELETE

@Entity(tableName = "User")
data class User(
        @PrimaryKey
        var userName: String,
        var favoriteFruit: String) {
}

@Dao
interface UserDao {

    @Query("SELECT * FROM User WHERE userName = :userName")
    fun getUserByName(userName: String): User

    @Query("SELECT * FROM USER")
    fun getUsers(): List<User>

    @Query("DELETE FROM USER")
    fun deleteAll()

    @Query("DELETE FROM USER WHERE userName = :userName")
    fun deleteUser(userName: String)

    @Update
    fun updateUser(user: User)

    @Update
    fun updateUsers(users: List<User>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(users: List<User>)
}