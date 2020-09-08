package com.laravelroomretrofitsync.api

import com.laravelroomretrofitsync.models.User
import com.laravelroomretrofitsync.models.UserData
import retrofit2.Response
import retrofit2.http.*

interface UserApi {

    // get all the users
    @GET("users")
    suspend fun getUserData(): Response<UserData>

    @POST("createUser")
    suspend fun createUser(@Body user: User): Response<User>

    @PUT("updateUser/{id}")
    suspend fun updateUser(@Path("id") id: Int, @Body user: User) : Response<User>

    @DELETE("deleteUser/{id}")
    suspend fun deleteUser(@Path("id") id: Int)
}