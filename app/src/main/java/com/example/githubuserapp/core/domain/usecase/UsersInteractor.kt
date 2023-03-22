package com.example.githubuserapp.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.githubuserapp.core.data.source.Resource
import com.example.githubuserapp.core.domain.model.User
import com.example.githubuserapp.core.domain.repository.InterfaceUsersRepository

class UsersInteractor(private val userRepository: InterfaceUsersRepository): UsersUseCase {

    override fun getAllUsers() = userRepository.getAllUsers()

//    override fun getUserDetail(id:Int)= userRepository.getUserDetail(id)

    override fun getFavoriteUsers() = userRepository.getFavoriteUsers()

    override fun setFavoriteUsers(user: User, state: Boolean) = userRepository.setFavoriteUsers(user, state)
}