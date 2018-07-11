package com.hiray.data

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Worker
import com.hiray.mvvm.model.User
import com.hiray.mvvm.model.UserDao

@Database(entities = [(User::class)], version = 1,exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        val DATABASE_NAME = "AppScaffoldDataBase"
        @Volatile
        var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                return INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): AppDataBase {
            return Room.databaseBuilder(context, AppDataBase::class.java, DATABASE_NAME)
                    .addCallback(object :Callback(){
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            //do some work like read local data ..etc
                        }
                    })
                    .build()
        }
    }


}