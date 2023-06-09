package com.example.githubuserapp.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Favorite")
@Parcelize
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "username")
    var username: String?  = null,
    @ColumnInfo(name = "name")
    var name: String? = null,
    @ColumnInfo(name = "photo")
    var photo: String? = null,
    @ColumnInfo(name = "followers")
    var followers: String? = null,
    @ColumnInfo(name = "following")
    var following: String? = null,
    @ColumnInfo(name = "company")
    var company: String? = null,
    @ColumnInfo(name = "location")
    var location: String? = null,
    @ColumnInfo(name = "repository")
    var repository: String? = null


) : Parcelable