package com.example.githubuserapp.feature.main

import androidx.lifecycle.ViewModel
import com.example.githubuserapp.core.domain.usecase.UsersUseCase

class HomeViewModel(usersUseCase: UsersUseCase) : ViewModel() {
    val users = usersUseCase.getAllUsers()
}