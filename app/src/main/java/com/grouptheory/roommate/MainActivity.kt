package com.grouptheory.roommate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
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

        // Assign FAB functionality
        findViewById<FloatingActionButton>(R.id.roommate_view_FAB).setOnClickListener {
            onRoommateViewClick()
        }

        // RecyclerView setup
        val recyclerView = findViewById<RecyclerView>(R.id.mainRepRecyclerView)
        val adapter = RepListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Begin observing data
        mainViewModel.startFetchingData()
        mainViewModel.userLiveData.observe(this) { roommate ->
            // Update local data
            roommate?.let {
                adapter.submitList(it.reps)
            }
        }
    }

    // OnClickListener for floating action button
    private fun onRoommateViewClick() {
        // Start roommate view activity
        val newIntent = Intent(this@MainActivity, PostRepActivity::class.java)
        newIntent.putExtra(PostRepActivity.USERNAME_EXTRA, "Carissa")
        startActivity(newIntent)
    }
}