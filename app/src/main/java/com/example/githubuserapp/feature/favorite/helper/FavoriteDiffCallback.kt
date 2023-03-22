package com.example.githubuserapp.feature.favorite.helper

import androidx.recyclerview.widget.DiffUtil
import com.example.githubuserapp.core.data.source.local.entity.UserEntity

class FavoriteDiffCallback(private val mOldUserEntityList: List<UserEntity>, private val mNewUserEntityList: List<UserEntity>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldUserEntityList.size
    }

    override fun getNewListSize(): Int {
        return mNewUserEntityList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldUserEntityList[oldItemPosition].id == mNewUserEntityList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldUserEntityList[oldItemPosition]
        val newEmployee = mNewUserEntityList[newItemPosition]
        return oldEmployee.name == newEmployee.name && oldEmployee.username == newEmployee.username
    }
}