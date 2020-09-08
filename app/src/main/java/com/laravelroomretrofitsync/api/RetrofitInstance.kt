package com.laravelroomretrofitsync.api

import com.laravelroomretrofitsync.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {

        private val retrofit by lazy {
            // making a logging interceptor
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            // creating a clint
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            // Retrofit Builder
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val api: UserApi by lazy {
            retrofit.create(UserApi::class.java)
        }

    }
}