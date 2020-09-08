package com.laravelroomretrofitsync.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laravelroomretrofitsync.models.User
import com.laravelroomretrofitsync.models.UserData
import com.laravelroomretrofitsync.repository.UserRepository
import com.laravelroomretrofitsync.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    val userData: MutableLiveData<Resource<UserData>> = MutableLiveData()
    val userLiveData: MutableLiveData<Resource<User>> = MutableLiveData()

    init {
        getUserData()
    }

    private fun getUserData() = viewModelScope.launch {
        userData.postValue(Resource.Loading())
        val response = userRepository.getUserData()
        userData.postValue(handleUserResponse(response))
    }

    fun createUser(user: User) = viewModelScope.launch {
        userLiveData.postValue(Resource.Loading())
        val response = userRepository.createUser(user)
        userLiveData.postValue(handleCreateUser(response))
    }

    fun updateUser(id: Int, user: User) = viewModelScope.launch {
        userLiveData.postValue(Resource.Loading())
        val response = userRepository.updateUser(id, user)
        Log.d("ERROR",response.toString())
//        userLiveData.postValue(handleUpdateRequest(response))
    }

    fun deleteUser(id: Int) = viewModelScope.launch {
        userData.postValue(Resource.Loading())
        val response = userRepository.deleteUser(id)
    }

    // handling response
    private fun handleUserResponse(response: Response<UserData>): Resource<UserData>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    // handling response
    private fun handleCreateUser(response: Response<User>): Resource<User> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    // handling response
    private fun handleUpdateRequest(response: Response<User>): Resource<User> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}