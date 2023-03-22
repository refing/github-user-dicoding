package com.example.githubuserapp.feature.detail

import androidx.lifecycle.ViewModel
import com.example.githubuserapp.core.domain.model.User
import com.example.githubuserapp.core.domain.usecase.UsersUseCase

class DetailViewModel(private val userUseCase: UsersUseCase) : ViewModel() {
    fun setFavoriteUser(user: User, newStatus:Boolean) =
        userUseCase.setFavoriteUsers(user, newStatus)
}