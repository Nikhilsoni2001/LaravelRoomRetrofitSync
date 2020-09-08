package com.laravelroomretrofitsync.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.laravelroomretrofitsync.repository.UserRepository

@Suppress("UNCHECKED_CAST")
class UserViewModelProviderFactory(private val userRepository: UserRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserViewModel(userRepository) as T
    }
}