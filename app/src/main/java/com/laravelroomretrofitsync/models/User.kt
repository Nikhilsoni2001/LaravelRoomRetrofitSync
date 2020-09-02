package com.laravelroomretrofitsync.models

data class User(
    val id: Int,
    val user_email: String,
    val user_phone: String,
    val user_status: String,
    val username: String
)