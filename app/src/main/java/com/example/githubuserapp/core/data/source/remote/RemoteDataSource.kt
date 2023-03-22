package com.example.githubuserapp.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubuserapp.BuildConfig
import com.example.githubuserapp.core.data.source.remote.network.ApiResponse
import com.example.githubuserapp.core.data.source.remote.network.ApiService
import com.example.githubuserapp.core.data.source.remote.response.DetailUserResponse
import com.example.githubuserapp.core.data.source.remote.response.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null
        private val token = BuildConfig.KEY

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    fun getAllUsers(): LiveData<ApiResponse<List<UserResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<UserResponse>>>()

        //get data from remote api
        val client = apiService.getUsers(token)

        client.enqueue(object : Callback<List<UserResponse>> {
            override fun onResponse(
                call: Call<List<UserResponse>>,
                response: Response<List<UserResponse>>
            ) {
                val dataArray = response.body()
                resultData.value = if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }

            override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }
        })

        return resultData
    }

//    fun getUserDetail(id:Int): LiveData<ApiResponse<DetailUserResponse>> {
//        val resultData = MutableLiveData<ApiResponse<DetailUserResponse>>()
//
//        //get data from remote api
//        val client = apiService.getUserDetail(id)
//
//        client.enqueue(object : Callback<DetailUserResponse> {
//            override fun onResponse(
//                call: Call<DetailUserResponse>,
//                response: Response<DetailUserResponse>
//            ) {
//                val dataArray = response.body()
//                resultData.value = if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
//            }
//
//            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
//                resultData.value = ApiResponse.Error(t.message.toString())
//                Log.e("RemoteDataSource", t.message.toString())
//            }
//        })
//
//        return resultData
//    }
}