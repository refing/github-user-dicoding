package com.example.githubuserapp.core.data.source.remote.network

import com.example.githubuserapp.core.data.source.remote.response.UserResponse
import com.example.githubuserapp.core.data.source.remote.response.preUserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    fun getUsers(
        @Header("Authorization") token: String?
    ): Call<List<preUserResponse>>

    @GET("users/{login}")
    fun getUserDetail(
        @Header("Authorization") token: String?,
        @Path("login") login: String
    ): Call<UserResponse>
}