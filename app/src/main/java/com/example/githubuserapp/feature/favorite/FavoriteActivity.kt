package com.example.githubuserapp.feature.favorite

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.core.ui.ListUserAdapter
import com.example.githubuserapp.databinding.ActivityFavoriteBinding
import com.example.githubuserapp.core.ui.ViewModelFactory
import com.example.githubuserapp.feature.detail.DetailActivity

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listUserAdapter = ListUserAdapter()
        listUserAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        val factory = ViewModelFactory.getInstance(this)
        favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

        favoriteViewModel.favoriteUsers.observe(this) { user ->
            binding.progressBar.visibility = View.GONE
            listUserAdapter.setData(user)
        }

        with(binding.rvFavorites) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = listUserAdapter
        }

        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvFavorites.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.rvFavorites.layoutManager = LinearLayoutManager(this)
        }

    }

}