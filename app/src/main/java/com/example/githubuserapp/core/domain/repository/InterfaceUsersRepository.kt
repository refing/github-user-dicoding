package com.example.githubuserapp.core.domain.repository

import androidx.lifecycle.LiveData
import com.example.githubuserapp.core.data.source.Resource
import com.example.githubuserapp.core.domain.model.User

interface InterfaceUsersRepository {
    fun getAllUsers(): LiveData<Resource<List<User>>>

    fun getFavoriteUsers(): LiveData<List<User>>

    fun setFavoriteUsers(user: User, state: Boolean)
}