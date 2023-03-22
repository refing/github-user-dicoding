package com.example.githubuserapp.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubuserapp.core.di.Injection
import com.example.githubuserapp.core.domain.usecase.UsersUseCase
import com.example.githubuserapp.feature.main.HomeViewModel

//import com.example.githubuserapp.feature.favorite.FavoriteViewModel
//import com.example.githubuserapp.feature.favorite.FavoriteAddViewModel

class ViewModelFactory private constructor(private val usersUseCase: UsersUseCase) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideUsersUseCase(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(usersUseCase) as T
            }
//            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
//                FavoriteViewModel(tourismUseCase) as T
//            }
//            modelClass.isAssignableFrom(DetailTourismViewModel::class.java) -> {
//                DetailTourismViewModel(tourismUseCase) as T
//            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}