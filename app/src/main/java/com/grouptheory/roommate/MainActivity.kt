package com.grouptheory.roommate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grouptheory.roommate.ui.main.ComplaintsListAdapter
import com.grouptheory.roommate.ui.main.MainViewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModel.MainViewModelFactory((application as RoomMateApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // RecyclerView setup
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = ComplaintsListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Begin observing data
        mainViewModel.startFetchingData()
        mainViewModel.userLiveData.observe(this) { roommates ->
            // Update local data
            roommates?.let {
                adapter.submitList(it.complaints)
            }
        }
    }
}