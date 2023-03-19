package com.example.githubuserapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.githubuserapp.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getParcelableExtra<User>(EXTRA_PERSON) as User
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

        val intentuser = intent.extras?.getParcelable<User>(EXTRA_PERSON)
        val sectionsPagerAdapter = SectionsPagerAdapter(this,intentuser)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }
    companion object {
        const val EXTRA_PERSON = "extra_person"
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }
}