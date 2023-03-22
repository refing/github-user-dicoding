package com.example.githubuserapp.core.data.source.remote.network

import com.example.githubuserapp.core.data.source.remote.response.DetailUserResponse
import com.example.githubuserapp.core.data.source.remote.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    fun getUsers(
        @Header("Authorization") token: String?
    ): Call<List<UserResponse>>

//    @GET("users/{id}")
//    fun getUserDetail(@Path("id") id: Int): Call<DetailUserResponse>
}