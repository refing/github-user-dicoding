package com.example.githubuserapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.databinding.FragmentFollowBinding
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject

class FollowFragment : Fragment() {
    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ListUserAdapter
    private var listUser: ArrayList<User> = ArrayList()
    private val token: String = "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"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ListUserAdapter(listUser)
        listUser.clear()
        if (arguments != null) {
            var argsec = arguments?.getInt(ARG_SECTION_NUMBER)
            val user = arguments?.getParcelable<User>(EXTRA_PERSON) as User
            getUserFollowers(user.username.toString(),argsec)
        }else{
            Log.d(TAG, "arguments null")
        }

    }
    private fun getUserFollowers(id: String,argsec:Int?) {
        binding.progressBar.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        client.addHeader("User-Agent", "request")
        client.addHeader("Authorization", "token ${token}")
        var fol : String = ""
        when (argsec) {
            1 -> fol = "followers"
            2 -> fol= "following"
        }
        val url = "https://api.github.com/users/${id}/${fol}"
        Log.d(TAG, url)
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
                binding.progressBar?.visibility = View.INVISIBLE
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
                    Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()
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
                Toast.makeText(activity, errorMessage, Toast.LENGTH_LONG).show()
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
                    val username: String? = jsonObject.getString("login").toString()
                    val name: String? = jsonObject.getString("name").toString()
                    val photo: String? = jsonObject.getString("avatar_url").toString()
                    val followers: String? = jsonObject.getString("followers").toString()
                    val following: String? = jsonObject.getString("following").toString()
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
                    Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()
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
                Toast.makeText(activity, errorMessage, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun showRecyclerList() {
        binding.rvFollow.layoutManager = LinearLayoutManager(activity)
        val listUserAdapter = ListUserAdapter(listUser)
        binding.rvFollow.adapter = listUserAdapter
    }

    companion object {
        const val ARG_SECTION_NUMBER = "section_number"
        private val TAG = FollowFragment::class.java.simpleName
        const val EXTRA_PERSON = "extra_person"
    }
}