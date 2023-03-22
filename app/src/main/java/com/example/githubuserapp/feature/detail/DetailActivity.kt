package com.example.githubuserapp.feature.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.githubuserapp.R
import com.example.githubuserapp.core.data.source.local.entity.UserEntity
import com.example.githubuserapp.core.domain.model.User
//import com.example.githubuserapp.core.ui.SectionsPagerAdapter
import com.example.githubuserapp.databinding.ActivityDetailBinding
import com.example.githubuserapp.core.ui.ViewModelFactory
import com.example.githubuserapp.feature.main.HomeViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
//    private lateinit var userDetailViewModel: FavoriteAddViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val user = intent.getParcelableExtra<User>(EXTRA_DATA) as User

        binding.tvFullname.text = user.name.toString()
        binding.tvUsername.text = user.username.toString()
        Glide.with(this)
            .load(user.photo)
            .circleCrop()
            .into(binding.imgPhoto)
        binding.tvFollowers.text = resources.getString(R.string.followers, user.followers, user.following)
        binding.tvCompany.text = resources.getString(R.string.company, user.company)
        binding.tvLocation.text = resources.getString(R.string.location, user.location)
        binding.tvRepository.text = resources.getString(R.string.repository, user.repository)

//        detailViewModel.getAllFavorites().observe(this) { favoriteList ->
//            if (favoriteList != null) {
//                for (data in favoriteList) {
//                    if (user.username == data.username) {
//                        buttonState = true
//                        binding?.fabFavorite?.setImageResource(R.drawable.ic_favorite)
//                    }
//                }
//            }
//        }
//        binding?.fabFavorite?.setOnClickListener {
//            if (!buttonState) {
//                buttonState = true
//                binding?.fabFavorite?.setImageResource(R.drawable.ic_favorite)
//                Toast.makeText(this, "User berhasil ditambahkan ke daftar favorite", Toast.LENGTH_SHORT).show()
//                userEntityUser.let { favoriteUser ->
//                    favoriteUser?.id = user.id
//                    favoriteUser?.username = user.username.toString()
//                    favoriteUser?.name = user.name.toString()
//                    favoriteUser?.photo = user.photo.toString()
//                    favoriteUser?.followers = user.followers.toString()
//                    favoriteUser?.following = user.following.toString()
//                    favoriteUser?.company = user.company.toString()
//                    favoriteUser?.location = user.location.toString()
//                    favoriteUser?.repository = user.repository.toString()
//                    userDetailViewModel.insert(favoriteUser as UserEntity)
//                }
//            } else {
//                buttonState = false
//                binding?.fabFavorite?.setImageResource(R.drawable.ic_unfavorite)
//                Toast.makeText(this, "User berhasil dihapus dari daftar favorite", Toast.LENGTH_SHORT).show()
//                userEntityUser.let { favoriteUser ->
//                    favoriteUser?.id = user.id
//                    favoriteUser?.username = user.username.toString()
//                    favoriteUser?.name = user.name.toString()
//                    favoriteUser?.photo = user.photo.toString()
//                    favoriteUser?.followers = user.followers.toString()
//                    favoriteUser?.following = user.following.toString()
//                    favoriteUser?.company = user.company.toString()
//                    favoriteUser?.location = user.location.toString()
//                    favoriteUser?.repository = user.repository.toString()
//                    userDetailViewModel.delete(favoriteUser as UserEntity)
//                }
//            }
//        }
////    })
//
//        val sectionsPagerAdapter = SectionsPagerAdapter(this,user)
//        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
//        viewPager.adapter = sectionsPagerAdapter
//        val tabs: TabLayout = findViewById(R.id.tabs)
//        TabLayoutMediator(tabs, viewPager) { tab, position ->
//            tab.text = resources.getString(TAB_TITLES[position])
//        }.attach()
        supportActionBar?.elevation = 0f
    }

}