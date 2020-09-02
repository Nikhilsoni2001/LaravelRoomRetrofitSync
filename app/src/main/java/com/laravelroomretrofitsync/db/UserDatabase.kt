package com.laravelroomretrofitsync.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [User::class],
    version = 1
)
abstract class UserDatabase: RoomDatabase() {

    // Function read() which will be automatically called by Room and will return our Dao
    abstract fun read(): UserDao

    // Companion object to create singleton of our database
    companion object {
        //Volatile means we only have one coroutine thread accessing our functions at a time
        @Volatile
        private var instance: UserDatabase ?= null

        //A lock for synchronized() function
        private val LOCK = Any()

        //This function gets automatically called whenever an instance of our database is declared i.e UserDatabase()
        operator fun invoke(context: Context) = instance?: synchronized(LOCK) {
            //If Instance is null, then call create database function and assign the newly created database to instance variable
            instance?: createDatabase(context).also {
                instance = it
            }
        }

        //Function to create our database
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,UserDatabase::class.java,"users.db").build()
    }

}