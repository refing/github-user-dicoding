package com.example.githubuserapp.feature.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuserapp.core.data.source.local.entity.UserEntity
import com.example.githubuserapp.core.domain.model.User
import com.example.githubuserapp.databinding.ItemRowUserBinding
import com.example.githubuserapp.feature.favorite.helper.FavoriteDiffCallback
//import com.example.githubuserapp.feature.detail.DetailActivity
import java.util.ArrayList

//class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
//    private val listUserEntities = ArrayList<UserEntity>()
//    fun setListFavorites(listUserEntities: List<UserEntity>) {
//        val diffCallback = FavoriteDiffCallback(this.listUserEntities, listUserEntities)
//        val diffResult = DiffUtil.calculateDiff(diffCallback)
//        this.listUserEntities.clear()
//        this.listUserEntities.addAll(listUserEntities)
//        diffResult.dispatchUpdatesTo(this)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
//        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return FavoriteViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
//        holder.bind(listUserEntities[position])
//    }
//
//    override fun getItemCount(): Int {
//        return listUserEntities.size
//    }
//
//    inner class FavoriteViewHolder(private val binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(userEntity: UserEntity) {
//            with(binding) {
//                tvItemName.text = userEntity.name
//                tvItemUsername.text = userEntity.username
//                val user = User(
//                    userEntity.id,
//                    userEntity.name,
//                    userEntity.username,
//                    userEntity.photo,
//                    userEntity.followers,
//                    userEntity.following,
//                    userEntity.company,
//                    userEntity.location,
//                    userEntity.repository,
//                )
//                itemView.setOnClickListener {
//                    val intent = Intent(itemView.context, DetailActivity::class.java)
//                    intent.putExtra(DetailActivity.EXTRA_PERSON, user)
//                    itemView.context.startActivity(intent)
//                }
//            }
//            Glide.with(itemView.context)
//                .load(userEntity.photo)
//                .circleCrop()
//                .into(binding.imgItemPhoto)
//        }
//    }
//}