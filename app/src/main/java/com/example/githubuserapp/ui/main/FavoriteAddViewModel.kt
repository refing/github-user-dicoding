package com.example.githubuserapp.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapp.database.Favorite
import com.example.githubuserapp.repository.FavoriteRepository

class FavoriteAddViewModel(application: Application) : ViewModel() {
    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(application)
    fun insert(user: Favorite) {
        mFavoriteRepository.insert(user)
    }

    fun getAllFavorites(): LiveData<List<Favorite>> = mFavoriteRepository.getAllFavorites()

    fun delete(user: Favorite) {
        mFavoriteRepository.delete(user)
    }
}