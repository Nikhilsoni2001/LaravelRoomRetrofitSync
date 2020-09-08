package com.laravelroomretrofitsync.models

data class User(
    val id: Int? = null,
    val user_email: String,
    val user_phone: String,
    val user_status: String,
    val user_name: String
)