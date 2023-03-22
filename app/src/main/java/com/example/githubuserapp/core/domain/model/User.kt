package com.example.githubuserapp.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class User(
    var id: Int = 0,
    var name: String? = null,
    var username: String? = null,
    var photo: String? = null,
    var followers: String? = null,
    var following: String? = null,
    var company: String? = null,
    var location: String? = null,
    var repository: String? = null,
    var isFavorite: Boolean = false
) : Parcelable