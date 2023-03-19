package com.example.githubuserapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var name: String,
    var username: String,
    var photo: Int,
    var followers: String,
    var following: String,
    var company: String,
    var location: String,
    var repository: String
) : Parcelable