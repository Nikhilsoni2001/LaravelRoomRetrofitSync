package com.laravelroomretrofitsync.repository

import com.laravelroomretrofitsync.api.RetrofitInstance
import com.laravelroomretrofitsync.db.UserDatabase
import com.laravelroomretrofitsync.models.User

class UserRepository(val db: UserDatabase) {

    suspend fun getUserData() = RetrofitInstance.api.getUserData()

    suspend fun createUser(user: User) = RetrofitInstance.api.createUser(user)

    suspend fun updateUser(id: Int, user: User) = RetrofitInstance.api.updateUser(id, user)

    suspend fun deleteUser(id: Int) = RetrofitInstance.api.deleteUser(id)

}