package com.example.githubuserapp.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.githubuserapp.core.data.source.local.entity.UserEntity

@Dao
interface UserDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insert(user: UserEntity)
//
//    @Delete
//    fun delete(user: UserEntity)

//    @Query("SELECT * FROM GithubUsers")
//    fun getAll(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM GithubUsers")
    fun getAllUsers(): LiveData<List<UserEntity>>

//    @Query("SELECT * FROM GithubUsers where id =:id")
//    fun getUserByID(id: Int): LiveData<UserEntity>

    @Query("SELECT * FROM GithubUsers where isFavorite = 1")
    fun getFavoriteUsers(): LiveData<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(user: List<UserEntity>)

    @Update
    fun updateFavoriteUsers(user: UserEntity)

}