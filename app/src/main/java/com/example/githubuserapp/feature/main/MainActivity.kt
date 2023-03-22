package com.example.githubuserapp.feature.main

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.BuildConfig
import com.example.githubuserapp.R
import com.example.githubuserapp.core.data.source.Resource
import com.example.githubuserapp.core.domain.model.User
import com.example.githubuserapp.core.ui.ListUserAdapter
import com.example.githubuserapp.core.ui.ViewModelFactory
import com.example.githubuserapp.databinding.ActivityMainBinding
import com.example.githubuserapp.databinding.ActivityThemeBinding
//import com.example.githubuserapp.feature.detail.DetailActivity
//import com.example.githubuserapp.feature.favorite.FavoriteActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
//    private lateinit var bindingSetting: ActivityThemeBinding
    private var listUser: ArrayList<User> = ArrayList()
    private var distinctUser: LinkedHashSet<User> = LinkedHashSet()
    private val token = BuildConfig.KEY
    private lateinit var homeViewModel: HomeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setTheme(R.style.Theme_GithubUserApp)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvUsers.setHasFixedSize(true)

        showRecyclerList()

//        bindingSetting = ActivityThemeBinding.inflate(layoutInflater)
//        val pref = SettingPreferences.getInstance(dataStore)
//        val settingViewModel = ViewModelProvider(this, SettingViewModelFactory(pref)).get(
//            SettingViewModel::class.java
//        )
//        settingViewModel.getThemeSettings().observe(this
//        ) { isDarkModeActive: Boolean ->
//            if (isDarkModeActive) {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//            } else {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//            }
//        }
//        getUser()
    }
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        val inflater = menuInflater
//        inflater.inflate(R.menu.option_menu, menu)
//
//        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        val searchView = menu.findItem(R.id.search).actionView as SearchView
//
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
//        searchView.queryHint = resources.getString(R.string.search_hint)
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String): Boolean {
//                if (query.isEmpty()) {
//                    return true
//                } else {
//                    listUser.clear()
//                    binding.progressBar.visibility = View.VISIBLE
//                    val client = AsyncHttpClient()
//                    client.addHeader("User-Agent", "request")
//                    client.addHeader("Authorization", "token ${token}")
//                    val url = "https://api.github.com/search/users?q=$query"
//                    client.get(url, object : AsyncHttpResponseHandler() {
//                        override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
//                            binding.progressBar.visibility = View.INVISIBLE
//                            val result = String(responseBody)
//                            Log.d(TAG, result)
//                            try {
//                                val jsonArray = JSONObject(result)
//                                val item = jsonArray.getJSONArray("items")
//                                for (i in 0 until item.length()) {
//                                    val jsonObject = item.getJSONObject(i)
//                                    val username: String = jsonObject.getString("login")
//                                    getUserDetail(username)
//                                }
//                            } catch (e: Exception) {
//                                Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
//                                e.printStackTrace()
//                            }
//                        }
//
//                        override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
//                            binding.progressBar.visibility = View.INVISIBLE
//                            val errorMessage = when (statusCode) {
//                                401 -> "$statusCode : Bad Request"
//                                403 -> "$statusCode : Forbidden"
//                                404 -> "$statusCode : Not Found"
//                                else -> "$statusCode : ${error.message}"
//                            }
//                            Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_LONG).show()
//                        }
//                    })
//                }
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String): Boolean {
//                return false
//            }
//        })
//
//        return true
//    }
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.action_setting -> {
//                val intent = Intent(this@MainActivity, ThemeActivity::class.java)
//                startActivity(intent)
//                return true
//            }
//            R.id.action_favorite -> {
//                val intent = Intent(this@MainActivity, FavoriteActivity::class.java)
//                startActivity(intent)
//                true
//            }
//            else -> true
//        }
//    }
//    private fun getUser() {
//        binding.progressBar.visibility = View.VISIBLE
//        val client = AsyncHttpClient()
//        client.addHeader("User-Agent", "request")
//        client.addHeader("Authorization", "token ${token}")
//        val url = "https://api.github.com/users"
//        client.get(url, object : AsyncHttpResponseHandler() {
//            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
//                binding.progressBar.visibility = View.INVISIBLE
//                val result = String(responseBody)
//                Log.d(TAG, result)
//
//                try {
//                    val jsonArray = JSONArray(result)
//                    for (i in 0 until jsonArray.length()) {
//                        val jsonObject = jsonArray.getJSONObject(i)
//                        val username: String = jsonObject.getString("login")
//                        getUserDetail(username)
//                    }
//                } catch (e: Exception) {
//                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
//                    e.printStackTrace()
//                }
//            }
//
//            override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
//                binding.progressBar.visibility = View.INVISIBLE
//                val errorMessage = when (statusCode) {
//                    401 -> "$statusCode : Bad Request - ${error.message}"
//                    403 -> "$statusCode : Forbidden - ${error.message}"
//                    404 -> "$statusCode : Not Found - ${error.message}"
//                    else -> "$statusCode : ${error.message}"
//                }
//                Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_LONG).show()
//            }
//        })
//    }
//    private fun getUserDetail(id: String) {
//        binding.progressBar.visibility = View.VISIBLE
//        val client = AsyncHttpClient()
//        client.addHeader("User-Agent", "request")
//        client.addHeader("Authorization", "token ${token}")
//        val url = "https://api.github.com/users/${id}"
//        client.get(url, object : AsyncHttpResponseHandler() {
//            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
//                binding.progressBar.visibility = View.INVISIBLE
//                val result = String(responseBody)
//                Log.d(TAG, result)
//                try {
//                    val jsonObject = JSONObject(result)
//                    val uid: Int = jsonObject.getString("id").toInt()
//                    val name: String? = jsonObject.getString("name").toString()
//                    val username: String? = jsonObject.getString("login").toString()
//                    val followers: String? = jsonObject.getString("followers").toString()
//                    val following: String? = jsonObject.getString("following").toString()
//                    val photo: String? = jsonObject.getString("avatar_url").toString()
//                    val company: String? = jsonObject.getString("company").toString()
//                    val location: String? = jsonObject.getString("location").toString()
//                    val repository: String? = jsonObject.getString("public_repos").toString()
//                    listUser.add(User(
//                        uid,
//                        name,
//                        username,
//                        photo,
//                        followers,
//                        following,
//                        company,
//                        location,
//                        repository,
//                    ))
//                    distinctUser.addAll(listUser)
//                    showRecyclerList()
//                } catch (e: Exception) {
//                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
//                    e.printStackTrace()
//                }
//            }
//            override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
//                binding.progressBar.visibility = View.INVISIBLE
//                val errorMessage = when (statusCode) {
//                    401 -> "$statusCode : Bad Request"
//                    403 -> "$statusCode : Forbidden"
//                    404 -> "$statusCode : Not Found"
//                    else -> "$statusCode : ${error.message}"
//                }
//                Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_LONG).show()
//            }
//        })
//    }

    private fun showRecyclerList() {
        val listUserAdapter = ListUserAdapter()
//        listUserAdapter.onItemClick = { selectedData ->
//            val intent = Intent(activity, DetailTourismActivity::class.java)
//            intent.putExtra(DetailTourismActivity.EXTRA_DATA, selectedData)
//            startActivity(intent)
//        }

        val factory = ViewModelFactory.getInstance(this)
        homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        homeViewModel.users.observe(this, { user ->
            if (user != null) {
                when (user) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        listUserAdapter.setData(user.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
//                        binding.viewError.root.visibility = View.VISIBLE
//                        binding.viewError.tvError.text = user.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        })

        with(binding.rvUsers) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = listUserAdapter
        }

        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvUsers.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.rvUsers.layoutManager = LinearLayoutManager(this)
        }
//        val listUserAdapter = ListUserAdapter(listUser)
//
//        binding.rvUsers.adapter = listUserAdapter
//        listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: User) {
//                val moveWithObjectIntent = Intent(this@MainActivity, DetailActivity::class.java)
//                moveWithObjectIntent.putExtra(DetailActivity.EXTRA_PERSON, data)
//                startActivity(moveWithObjectIntent)
//            }
//        })
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}