package com.example.githubuserapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.githubuserapp.database.Favorite
import com.example.githubuserapp.database.FavoriteDao
import com.example.githubuserapp.database.FavoriteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(application: Application) {
    private val mFavoriteDao: FavoriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteRoomDatabase.getDatabase(application)
        mFavoriteDao = db.favoriteDao()
    }

    fun getAllFavorites(): LiveData<List<Favorite>> = mFavoriteDao.getAll()

    fun insert(user: Favorite) {
        executorService.execute { mFavoriteDao.insert(user) }
    }

    fun delete(user: Favorite) {
        executorService.execute { mFavoriteDao.delete(user) }
    }
}