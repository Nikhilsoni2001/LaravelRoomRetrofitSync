package com.laravelroomretrofitsync.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    //Function to create a new user
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun create(user: User)

    //Function to read the users from database
    @Query("SELECT * FROM users")
    fun read(): LiveData<List<User>>

    //Function to update a user
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(user: User)

    //Function to delete a user
    @Delete
    suspend fun delete(user: User)

}