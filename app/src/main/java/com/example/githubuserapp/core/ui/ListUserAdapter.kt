package com.example.githubuserapp.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuserapp.R
import com.example.githubuserapp.core.domain.model.User
import com.example.githubuserapp.databinding.ItemRowUserBinding
import java.util.ArrayList

class ListUserAdapter : RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {

    private var listData = ArrayList<User>()
    var onItemClick: ((User) -> Unit)? = null

    fun setData(newListData: List<User>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row_user, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemRowUserBinding.bind(itemView)
        fun bind(data: User) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.photo)
                    .into(imgItemPhoto)
                tvItemName.text = data.name
                tvItemUsername.text = data.username

            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
//        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return ListViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//        val (id, name, username, photo) = listUser[position]
//        Glide.with(holder.itemView.context)
//            .load(photo)
//            .circleCrop()
//            .into(holder.binding.imgItemPhoto)
//        holder.binding.tvItemUsername.text = username
//        holder.binding.tvItemName.text = name
//        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listUser[holder.adapterPosition]) }
//
//    }
//
//    override fun getItemCount(): Int = listUser.size
//
//    class ListViewHolder(var binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root)
//
//    private lateinit var onItemClickCallback: OnItemClickCallback
//
//    interface OnItemClickCallback {
//        fun onItemClicked(data: User)
//    }
//    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
//        this.onItemClickCallback = onItemClickCallback
//    }
}