package com.example.githubuserapp

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.databinding.ActivityMainBinding
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var listUser: ArrayList<User> = ArrayList()
    private val token: String = "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"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvUsers.setHasFixedSize(true)

        getUser()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isEmpty()) {
                    return true
                } else {
                    listUser.clear()
                    binding.progressBar.visibility = View.VISIBLE
                    val client = AsyncHttpClient()
                    client.addHeader("User-Agent", "request")
                    client.addHeader("Authorization", "token ${token}")
                    val url = "https://api.github.com/search/users?q=$query"
                    client.get(url, object : AsyncHttpResponseHandler() {
                        override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
                            binding.progressBar.visibility = View.INVISIBLE
                            val result = String(responseBody)
                            Log.d(TAG, result)
                            try {
                                val jsonArray = JSONObject(result)
                                val item = jsonArray.getJSONArray("items")
                                for (i in 0 until item.length()) {
                                    val jsonObject = item.getJSONObject(i)
                                    val username: String = jsonObject.getString("login")
                                    getUserDetail(username)
                                }
                            } catch (e: Exception) {
                                Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
                                e.printStackTrace()
                            }
                        }

                        override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
                            binding.progressBar.visibility = View.INVISIBLE
                            val errorMessage = when (statusCode) {
                                401 -> "$statusCode : Bad Request"
                                403 -> "$statusCode : Forbidden"
                                404 -> "$statusCode : Not Found"
                                else -> "$statusCode : ${error.message}"
                            }
                            Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_LONG).show()
                        }
                    })
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }
    private fun getUser() {
        binding.progressBar.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        client.addHeader("User-Agent", "request")
        client.addHeader("Authorization", "token ${token}")
        val url = "https://api.github.com/users"
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
                binding.progressBar.visibility = View.INVISIBLE
                val result = String(responseBody)
                Log.d(TAG, result)

                try {
                    val jsonArray = JSONArray(result)
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val username: String = jsonObject.getString("login")
                        getUserDetail(username)
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
                binding.progressBar.visibility = View.INVISIBLE
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request - ${error.message}"
                    403 -> "$statusCode : Forbidden - ${error.message}"
                    404 -> "$statusCode : Not Found - ${error.message}"
                    else -> "$statusCode : ${error.message}"
                }
                Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_LONG).show()
            }
        })
    }
    private fun getUserDetail(id: String) {
        binding.progressBar.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        client.addHeader("User-Agent", "request")
        client.addHeader("Authorization", "token ${token}")
        val url = "https://api.github.com/users/${id}"
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
                binding.progressBar.visibility = View.INVISIBLE
                val result = String(responseBody)
                Log.d(TAG, result)
                try {
                    val jsonObject = JSONObject(result)
                    val name: String? = jsonObject.getString("name").toString()
                    val username: String? = jsonObject.getString("login").toString()
                    val followers: String? = jsonObject.getString("followers").toString()
                    val following: String? = jsonObject.getString("following").toString()
                    val photo: String? = jsonObject.getString("avatar_url").toString()
                    val company: String? = jsonObject.getString("company").toString()
                    val location: String? = jsonObject.getString("location").toString()
                    val repository: String? = jsonObject.getString("public_repos").toString()
                    listUser.add(
                        User(
                            name,
                            username,
                            photo,
                            followers,
                            following,
                            company,
                            location,
                            repository,
                        )
                    )
                    showRecyclerList()
                } catch (e: Exception) {
                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }
            override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
                binding.progressBar.visibility = View.INVISIBLE
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error.message}"
                }
                Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun showRecyclerList() {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvUsers.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.rvUsers.layoutManager = LinearLayoutManager(this)
        }
        val listUserAdapter = ListUserAdapter(listUser)
        binding.rvUsers.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                val moveWithObjectIntent = Intent(this@MainActivity, DetailActivity::class.java)
                moveWithObjectIntent.putExtra(DetailActivity.EXTRA_PERSON, data)
                startActivity(moveWithObjectIntent)
            }
        })
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}