package com.laravelroomretrofitsync.api

import com.laravelroomretrofitsync.models.UserData
import retrofit2.Response
import retrofit2.http.GET

interface UserApi {

    // get all the users
    @GET("api/users")
    suspend fun getUserData(): Response<UserData>
}