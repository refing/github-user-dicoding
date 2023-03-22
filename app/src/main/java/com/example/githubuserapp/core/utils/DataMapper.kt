package com.example.githubuserapp.core.utils

import com.example.githubuserapp.core.data.source.local.entity.UserEntity
import com.example.githubuserapp.core.data.source.remote.response.UserResponse
import com.example.githubuserapp.core.domain.model.User

object DataMapper {
    fun mapResponsesToEntities(input: List<UserResponse>): List<UserEntity> {
        val userList = ArrayList<UserEntity>()
        input.map {
            val user = UserEntity(
                id = it.id,
                username = it.login,
                name = it.name,
                photo = it.avatar_url,
                followers = it.followers,
                following = it.following,
                company = it.company,
                location = it.location,
                repository = it.public_repos,
                isFavorite = false
            )
            userList.add(user)
        }
        return userList
    }

    fun mapEntitiesToDomain(input: List<UserEntity>): List<User> =
        input.map {
            User(
                id = it.id,
                username = it.username,
                name = it.name,
                photo = it.photo,
                followers = it.followers,
                following = it.following,
                company = it.company,
                location = it.location,
                repository = it.repository,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: User) = UserEntity(
        id = input.id,
        username = input.username,
        name = input.name,
        photo = input.photo,
        followers = input.followers,
        following = input.following,
        company = input.company,
        location = input.location,
        repository = input.repository,
        isFavorite = input.isFavorite
    )
}