package com.example.githubuserapp.feature.favorite

import androidx.lifecycle.ViewModel
import com.example.githubuserapp.core.domain.usecase.UsersUseCase

class FavoriteViewModel(userUseCase: UsersUseCase) : ViewModel() {
    val favoriteUsers = userUseCase.getFavoriteUsers()

}