package com.example.githubuserapp.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.githubuserapp.core.data.source.Resource
import com.example.githubuserapp.core.domain.model.User
import com.example.githubuserapp.core.domain.repository.InterfaceUsersRepository

interface UsersUseCase {
    fun getAllUsers(): LiveData<Resource<List<User>>>
//    fun getUserDetail(id:Int): LiveData<Resource<User>>
    fun getFavoriteUsers(): LiveData<List<User>>
    fun setFavoriteUsers(user: User, state: Boolean)
}