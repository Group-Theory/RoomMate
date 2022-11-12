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
import com.grouptheory.roommate.DataClasses.Rep
import com.grouptheory.roommate.R
import com.grouptheory.roommate.RoomMateApplication
import com.grouptheory.roommate.ui.main.MainViewModel

class PostRepActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModel.MainViewModelFactory((application as RoomMateApplication).repository)
    }
    private var username: String = ""
    private var points: Int = 0
    private lateinit var descriptionTextView: TextView
    private lateinit var postButton : Button
    private lateinit var pointsBar: SeekBar
    private lateinit var pointDisplay: TextView
    private lateinit var usersSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_rep)
        username = intent.getStringExtra(USERNAME_EXTRA).toString()
        descriptionTextView = findViewById(R.id.editDescription)
        postButton = findViewById(R.id.postButton)
        pointsBar = findViewById(R.id.pointsSeekBar)
        pointDisplay = findViewById(R.id.pointsDisplay)
        usersSpinner = findViewById(R.id.usersSpinner)
        pointDisplay.text = "Points: 0"

        pointsBar.setOnSeekBarChangeListener( object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                pointDisplay.text = "Points: $progress"
                points = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //none
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //nothing
            }

        })

        postButton.setOnClickListener {
            val newRep: Rep = Rep(description = descriptionTextView.text.toString(), points = points)
            Log.d("onClick listener", newRep.toString())
            Log.d("onClick listener", username)

            mainViewModel.addNewRep(newRep, username)
            finish()
        }

        var arrayAdapter : ArrayAdapter<String>
        mainViewModel.roommatesLiveData.observe(this) {
            var arrayOfUserNames : Array<String> = emptyArray()
            it.forEach { user ->
                Log.d("username", user.userName)
                arrayOfUserNames.plus(user.userName)
            }
            arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayOfUserNames)
            usersSpinner.adapter = arrayAdapter
        }

    }

    companion object{
        const val USERNAME_EXTRA = "com.grouptheory.roommate.createRep.USERNAME_EXTRA"
    }
}