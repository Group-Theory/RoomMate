package com.grouptheory.roommate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grouptheory.roommate.DataClasses.Rep
import com.grouptheory.roommate.DataClasses.User
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
                adapter.submitList(it.reps)
            }
        }
        val reps: List<Rep> = listOf(
            Rep(id = 0, "He does not fortnite dance", 300000, 300000, 0, -16),
            Rep(id = 1, "Did not shower (CS Major)", 300001, 300001, 0, -13),
            Rep(id = 27, "Took out the trash", 300000, 300000, 0, 5),
            Rep(id = 75, "Did not join ACM", 300000, 300000, 0, -38)
        )

        val user1 = User(userName = "Joe", "pw", score = 100, )
        mainViewModel.insertUser(user1)
    }
}