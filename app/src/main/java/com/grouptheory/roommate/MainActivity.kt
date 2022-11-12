package com.grouptheory.roommate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.grouptheory.roommate.DataClasses.Chore
import com.grouptheory.roommate.DataClasses.Complaint
import com.grouptheory.roommate.DataClasses.User
import com.grouptheory.roommate.ui.main.MainFragment
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
        val complaints: List<Complaint> = listOf(
            Complaint(id = 0, "He does not fortnite dance", 300000, 0, 20, "nothing extra"),
            Complaint(id = 1, "Did not shower (CS Major)", 300001, 0, 10, "nothing extra"),
            Complaint(id = 75, "Did not join ACM", 300000, 0, 20, "nothing extra")
        )
        val chores: List<Chore> = listOf(
            Chore(id = 0, "Take out the trash", 300, 0, 0, 10),
            Chore(id = 1, "Buy new milk", 300, 0, 0, 10),
            Chore(id = 15, "Vacuum floors", 300, 0, 0, 10),
        )

        val user1: User = User(userName = "Joe", "pw", complaints=complaints, chores = chores, score = 100)
        mainViewModel.insertUser(user1)
    }
}