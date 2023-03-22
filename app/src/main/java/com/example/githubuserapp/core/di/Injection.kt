package com.example.githubuserapp.core.di

import android.content.Context
import com.example.githubuserapp.core.data.source.local.LocalDataSource
import com.example.githubuserapp.core.data.source.local.room.UserRoomDatabase
import com.example.githubuserapp.core.data.source.remote.RemoteDataSource
import com.example.githubuserapp.core.data.source.remote.network.ApiConfig
import com.example.githubuserapp.core.data.source.repository.UsersRepository
import com.example.githubuserapp.core.domain.repository.InterfaceUsersRepository
import com.example.githubuserapp.core.domain.usecase.UsersInteractor
import com.example.githubuserapp.core.domain.usecase.UsersUseCase
import com.example.githubuserapp.core.utils.AppExecutors

object Injection {
    private fun provideRepository(context: Context): InterfaceUsersRepository {
        val database = UserRoomDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.userDao())
        val appExecutors = AppExecutors()

        return UsersRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideUsersUseCase(context: Context): UsersUseCase {
        val repository = provideRepository(context)
        return UsersInteractor(repository)
    }
}
