package com.grouptheory.roommate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grouptheory.roommate.DataClasses.Rep
import com.grouptheory.roommate.DataClasses.User
import com.grouptheory.roommate.createRep.PostRepActivity
import com.grouptheory.roommate.ui.main.MainViewModel
import com.grouptheory.roommate.ui.main.RepListAdapter

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModel.MainViewModelFactory((application as RoomMateApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // RecyclerView setup
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = RepListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Begin observing data
        mainViewModel.startFetchingData()
        mainViewModel.userLiveData.observe(this) { roommates ->
            // Update local data
            roommates?.let {
                adapter.submitList(it.reps)
            }
        }

        var newIntent = Intent(this@MainActivity, PostRepActivity::class.java)
        intent.putExtra(PostRepActivity.USERNAME_EXTRA, "Carissa")
        startActivity(newIntent)
    }
}