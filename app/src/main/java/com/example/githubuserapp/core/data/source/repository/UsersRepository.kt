package com.example.githubuserapp.core.data.source.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.githubuserapp.core.data.source.NetworkBoundResource
import com.example.githubuserapp.core.data.source.Resource
import com.example.githubuserapp.core.data.source.local.LocalDataSource
import com.example.githubuserapp.core.data.source.remote.RemoteDataSource
import com.example.githubuserapp.core.data.source.remote.network.ApiResponse
import com.example.githubuserapp.core.data.source.remote.response.UserResponse
import com.example.githubuserapp.core.domain.model.User
import com.example.githubuserapp.core.domain.repository.InterfaceUsersRepository
import com.example.githubuserapp.core.utils.AppExecutors
import com.example.githubuserapp.core.utils.DataMapper

class UsersRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : InterfaceUsersRepository {
    companion object {
        @Volatile
        private var instance: UsersRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): UsersRepository =
            instance ?: synchronized(this) {
                instance ?: UsersRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getAllUsers(): LiveData<Resource<List<User>>> =
        object : NetworkBoundResource<List<User>, List<UserResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<User>> {
                return Transformations.map(localDataSource.getAllUsers()) {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<User>?): Boolean =
//                data == null || data.isEmpty()
                true // ganti dengan true jika ingin selalu mengambil data dari internet

            override fun createCall(): LiveData<ApiResponse<List<UserResponse>>> =
                remoteDataSource.getAllUsers()

            override fun saveCallResult(data: List<UserResponse>) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertUsers(tourismList)
            }
        }.asLiveData()

    override fun getFavoriteUsers(): LiveData<List<User>> {
        return Transformations.map(localDataSource.getFavoriteUsers()) {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteUsers(user: User, state: Boolean) {
        val tourismEntity = DataMapper.mapDomainToEntity(user)
        appExecutors.diskIO().execute { localDataSource.setFavoriteUsers(tourismEntity, state) }
    }
}
