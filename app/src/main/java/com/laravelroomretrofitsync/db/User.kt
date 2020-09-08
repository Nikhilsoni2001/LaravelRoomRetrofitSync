package com.laravelroomretrofitsync.db

import androidx.room.Entity
import androidx.room.PrimaryKey


// This represents a table of our User Database
@Entity(tableName = "Users")
data class User (
    @PrimaryKey
    val user_id: Int,
    val user_name: String,
    val user_phone: String,
    val user_email: String,
    val user_status: String
)