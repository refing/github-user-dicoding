package com.example.githubuserapp.core.data.source.local

import androidx.lifecycle.LiveData
import com.example.githubuserapp.core.data.source.local.entity.UserEntity
import com.example.githubuserapp.core.data.source.local.room.UserDao

class LocalDataSource private constructor(private val userDao: UserDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(userDao: UserDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(userDao)
            }
    }

    fun getAllUsers(): LiveData<List<UserEntity>> = userDao.getAllUsers()

//    fun getUserByID(id: Int): LiveData<UserEntity> = userDao.getUserByID(id)

    fun getFavoriteUsers(): LiveData<List<UserEntity>> = userDao.getFavoriteUsers()

    fun insertUsers(userList: List<UserEntity>) = userDao.insertUsers(userList)

    fun setFavoriteUsers(user: UserEntity, newState: Boolean) {
        user.isFavorite = newState
        userDao.updateFavoriteUsers(user)
    }
}
//class FavoriteRepository(application: Application) {
//    private val mFavoriteDao: FavoriteDao
//    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
//
//    init {
//        val db = FavoriteRoomDatabase.getDatabase(application)
//        mFavoriteDao = db.favoriteDao()
//    }
//
//    fun getAllFavorites(): LiveData<List<Favorite>> = mFavoriteDao.getAll()
//
//    fun insert(user: Favorite) {
//        executorService.execute { mFavoriteDao.insert(user) }
//    }
//
//    fun delete(user: Favorite) {
//        executorService.execute { mFavoriteDao.delete(user) }
//    }
//}