package com.laravelroomretrofitsync.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.laravelroomretrofitsync.R
import com.laravelroomretrofitsync.db.UserDatabase
import com.laravelroomretrofitsync.repository.UserRepository
import com.laravelroomretrofitsync.ui.fragments.UserFragment

class UserActivity : AppCompatActivity() {

    lateinit var  viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val userRepository = UserRepository(UserDatabase(this))
        val viewModelProviderFactory = UserViewModelProviderFactory(userRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(UserViewModel::class.java)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, UserFragment()).commit()

    }
}