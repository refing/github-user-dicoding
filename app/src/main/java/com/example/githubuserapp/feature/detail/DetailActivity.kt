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
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

//class DetailActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityDetailBinding
//    private var buttonState: Boolean = false
//    private var userEntityUser: UserEntity? = null
//    private lateinit var userDetailViewModel: FavoriteAddViewModel
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityDetailBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        userDetailViewModel = obtainViewModel(this@DetailActivity)
//
//        val user = intent.getParcelableExtra<User>(EXTRA_PERSON) as User
//        userEntityUser = UserEntity()
//
//        binding.tvFullname.text = user.name.toString()
//        binding.tvUsername.text = user.username.toString()
//        Glide.with(this)
//            .load(user.photo)
//            .circleCrop()
//            .into(binding.imgPhoto)
//        binding.tvFollowers.text = resources.getString(R.string.followers, user.followers, user.following)
//        binding.tvCompany.text = resources.getString(R.string.company, user.company)
//        binding.tvLocation.text = resources.getString(R.string.location, user.location)
//        binding.tvRepository.text = resources.getString(R.string.repository, user.repository)
//
//        userDetailViewModel.getAllFavorites().observe(this) { favoriteList ->
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
//        supportActionBar?.elevation = 0f
//    }
//    private fun obtainViewModel(activity: AppCompatActivity): FavoriteAddViewModel {
//        val factory = ViewModelFactory.getInstance(activity.application)
//        return ViewModelProvider(activity, factory).get(FavoriteAddViewModel::class.java)
//    }
//    companion object {
//        const val EXTRA_PERSON = "extra_person"
//        @StringRes
//        private val TAB_TITLES = intArrayOf(
//            R.string.tab_text_1,
//            R.string.tab_text_2
//        )
//    }
//}