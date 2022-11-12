package com.grouptheory.roommate.createRep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grouptheory.roommate.R
import com.grouptheory.roommate.RoomMateApplication
import com.grouptheory.roommate.ui.main.MainViewModel
import com.grouptheory.roommate.util.replaceFragmentInActivity

class PostRepActivity : AppCompatActivity() {
    // ViewModel for accessing live data
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModel.MainViewModelFactory((application as RoomMateApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_rep)

        // RecyclerView setup
        val userRecyclerView = findViewById<RecyclerView>(R.id.roommatesRecyclerView)
        val adapter = UserListAdapter()
        userRecyclerView.adapter = adapter
        userRecyclerView.layoutManager = LinearLayoutManager(this)

        // Begin observing live data
        mainViewModel.startFetchingData()
        // Show list of users
        mainViewModel.roommatesLiveData.observe(this) { roommates ->
            roommates?.let {
                adapter.submitList(roommates)
            }
        }
    }

    // Inflate the fragment that allows specifying a rep to post
    private fun getPostRepFragment(username: String): PostRepFragment {

        return PostRepFragment.newInstance(username).also {
            replaceFragmentInActivity(it, R.id.postRepFragmentContainerView)
        }
    }

    // User clicks on a roommate
    fun onUserClick(view: View) {
        val username = view.tag as String

        getPostRepFragment(username)
    }

    companion object{
        const val USERNAME_EXTRA = "com.grouptheory.roommate.createRep.USERNAME_EXTRA"
    }
}