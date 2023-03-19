package com.example.githubuserapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: Favorite)

    @Delete
    fun delete(user: Favorite)

    @Query("SELECT * FROM Favorite")
    fun getAll(): LiveData<List<Favorite>>

}