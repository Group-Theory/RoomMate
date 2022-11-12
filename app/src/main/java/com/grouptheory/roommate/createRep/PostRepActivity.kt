package com.grouptheory.roommate.createRep

import android.database.DataSetObserver
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.AbsSpinner
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.SpinnerAdapter
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grouptheory.roommate.DataClasses.Rep
import com.grouptheory.roommate.R
import com.grouptheory.roommate.RoomMateApplication
import com.grouptheory.roommate.ui.main.MainViewModel
import com.grouptheory.roommate.util.replaceFragmentInActivity

class PostRepActivity : AppCompatActivity() {
    // ViewModel for accessing live data
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModel.MainViewModelFactory((application as RoomMateApplication).repository)
    }

    // Fragment that is pulled up when user is selected from list
    private var postRepFragment: PostRepFragment? = null

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

    private fun getPostRepFragment(username: String): PostRepFragment {

        return PostRepFragment.newInstance(username).also {
            replaceFragmentInActivity(it, R.id.postRepFragmentContainerView)
        }
    }

    fun onUserClick(view: View) {
        val username = view.tag as String

        getPostRepFragment(username)
    }

    companion object{
        const val USERNAME_EXTRA = "com.grouptheory.roommate.createRep.USERNAME_EXTRA"
    }
}