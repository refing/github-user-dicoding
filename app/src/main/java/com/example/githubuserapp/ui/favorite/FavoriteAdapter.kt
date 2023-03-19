package com.example.githubuserapp.ui.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuserapp.database.Favorite
import com.example.githubuserapp.databinding.ItemRowUserBinding
import com.example.githubuserapp.helper.FavoriteDiffCallback
import com.example.githubuserapp.ui.main.DetailActivity
import com.example.githubuserapp.ui.main.User
import java.util.ArrayList

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    private val listFavorites = ArrayList<Favorite>()
    fun setListFavorites(listFavorites: List<Favorite>) {
        val diffCallback = FavoriteDiffCallback(this.listFavorites, listFavorites)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listFavorites.clear()
        this.listFavorites.addAll(listFavorites)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(listFavorites[position])
    }

    override fun getItemCount(): Int {
        return listFavorites.size
    }

    inner class FavoriteViewHolder(private val binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(favorite: Favorite) {
            with(binding) {
                tvItemName.text = favorite.name
                tvItemUsername.text = favorite.username
                val user = User(
                    favorite.id,
                    favorite.name,
                    favorite.username,
                    favorite.photo,
                    favorite.followers,
                    favorite.following,
                    favorite.company,
                    favorite.location,
                    favorite.repository,
                )
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_PERSON, user)
                    itemView.context.startActivity(intent)
                }
            }
            Glide.with(itemView.context)
                .load(favorite.photo)
                .circleCrop()
                .into(binding.imgItemPhoto)
        }
    }
}