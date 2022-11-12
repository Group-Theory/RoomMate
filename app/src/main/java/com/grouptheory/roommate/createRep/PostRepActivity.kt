package com.grouptheory.roommate.createRep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import com.grouptheory.roommate.R

class PostRepActivity : AppCompatActivity() {
    private var username: String = ""
    private lateinit var postButton : Button
    private lateinit var pointsBar: SeekBar
    private lateinit var pointDisplay: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_rep)
        username = intent.getStringExtra(USERNAME_EXTRA).toString()
        postButton = findViewById(R.id.postButton)
        pointsBar = findViewById(R.id.pointsSeekBar)
        pointDisplay = findViewById(R.id.pointsDisplay)
        pointDisplay.text = "Points: 0"

        pointsBar.setOnSeekBarChangeListener( object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                pointDisplay.text = "Points: $progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                //none
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                //nothing
            }

        })
    }

    companion object{
        const val USERNAME_EXTRA = "com.grouptheory.roommate.createRep.USERNAME_EXTRA"
    }
}