package com.laravelroomretrofitsync.db

import androidx.room.Entity


// This represents a table of our User Database
@Entity(tableName = "Users")
data class User (
    val user_id: Int,
    val user_name: String,
    val user_phone: String,
    val user_email: String,
    val user_status: String
)