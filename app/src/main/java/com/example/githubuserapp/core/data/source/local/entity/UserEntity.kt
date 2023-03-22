package com.example.githubuserapp.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "GithubUsers")
data class UserEntity(
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
    var repository: String? = null,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false


)