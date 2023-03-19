package com.example.githubuserapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_PERSON = "extra_person"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val tvFullname: TextView = findViewById(R.id.tv_fullname)
        val tvUsername: TextView = findViewById(R.id.tv_username)
        val ivImage: ImageView = findViewById(R.id.img_photo)
        val tvFollowers: TextView = findViewById(R.id.tv_followers)
        val tvCompany: TextView = findViewById(R.id.tv_company)
        val tvLocation: TextView = findViewById(R.id.tv_location)
        val tvRepository: TextView = findViewById(R.id.tv_repository)

        val user = intent.getParcelableExtra<User>(EXTRA_PERSON) as User
        tvFullname.text = user.name.toString()
        tvUsername.text = user.username.toString()
        ivImage.setImageResource(user.photo)
        tvFollowers.text = "${user.followers.toString()} Followers   ${user.following.toString()} Following"
        tvCompany.text = "Company : ${user.company.toString()}"
        tvLocation.text = "Location : ${user.location.toString()}"
        tvRepository.text = "${user.repository.toString()} Repositories"
    }
}