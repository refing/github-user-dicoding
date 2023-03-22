package com.example.githubuserapp.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubuserapp.BuildConfig
import com.example.githubuserapp.core.data.source.remote.network.ApiResponse
import com.example.githubuserapp.core.data.source.remote.network.ApiService
import com.example.githubuserapp.core.data.source.remote.response.UserResponse
import com.example.githubuserapp.core.data.source.remote.response.preUserResponse
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
        val resultDataListDetail = MutableLiveData<ApiResponse<List<UserResponse>>>()
        val arraytemp = ArrayList<UserResponse>()
        //get data from remote api
        val client = apiService.getUsers(token)
        client.enqueue(object : Callback<List<preUserResponse>> {
            override fun onResponse(
                call: Call<List<preUserResponse>>,
                response: Response<List<preUserResponse>>
            ) {
                val dataArray = response.body()

                if (dataArray != null) {
                    Log.e("debugrefi listsize",dataArray.size.toString())
                    for (item in dataArray){
                        val client2 = apiService.getUserDetail(token,item.login)
                        client2.enqueue(object : Callback<UserResponse> {
                            override fun onResponse(
                                call: Call<UserResponse>,
                                response: Response<UserResponse>
                            ) {
                                val dataArrayDetail = response.body()
                                if (dataArrayDetail != null) {
                                    arraytemp.add(dataArrayDetail)
                                }
                            }

                            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                                resultDataListDetail.value = ApiResponse.Error(t.message.toString())
                                Log.e("RemoteDataSource", t.message.toString())
                            }
                        })
                    }
                    Log.e("debugrefi listtempapl",arraytemp.toString())
                    Log.e("debugrefi size",arraytemp.size.toString())
                    resultDataListDetail.value = ApiResponse.Success(arraytemp)
                }else{
                    resultDataListDetail.value = ApiResponse.Empty
                }
            }

            override fun onFailure(call: Call<List<preUserResponse>>, t: Throwable) {
                resultDataListDetail.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }
        })

        return resultDataListDetail
    }

}