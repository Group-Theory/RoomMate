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

        val newRep = Rep(id = 0, "Killed my dog", 300, 0, 0, -10)

//        mainViewModel.addNewRep(newRep, "Steve")
    }
}