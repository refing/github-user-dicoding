package com.example.githubuserapp.feature.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubuserapp.core.data.source.local.entity.UserEntity

//class FavoriteAddViewModel(application: Application) : ViewModel() {
//    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(application)
//    fun insert(user: UserEntity) {
//        mFavoriteRepository.insert(user)
//    }
//
//    fun getAllFavorites(): LiveData<List<UserEntity>> = mFavoriteRepository.getAllFavorites()
//
//    fun delete(user: UserEntity) {
//        mFavoriteRepository.delete(user)
//    }
//}