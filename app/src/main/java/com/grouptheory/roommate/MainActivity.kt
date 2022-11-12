package com.grouptheory.roommate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.grouptheory.roommate.DataClasses.Rep
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

        mainViewModel.startFetchingData()

        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
        val complaints: List<Complaint> = listOf(
            Complaint(id = 0, "Stays up too late", 300000, 0, 20, "nothing extra"),
            Complaint(id = 1, "Too country", 300001, 0, 10, "nothing extra"),
            Complaint(id = 75, "Did not apply for grad school", 300000, 0, 20, "nothing extra")
        )
        val reps: List<Rep> = listOf(
            Rep(id = 0, "Wash the car", 300, 0, 0, 10),
            Rep(id = 1, "Dust the living room", 300, 0, 0, 10),
            Rep(id = 15, "Turn on the heat", 300, 0, 0, 1),
        )

        val user3: User = User(userName = "Carissa", "pw",  reps = reps, score = 100)
        mainViewModel.insertUser(user3)
    }
}