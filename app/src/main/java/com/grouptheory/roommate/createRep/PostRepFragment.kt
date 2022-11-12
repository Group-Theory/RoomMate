package com.grouptheory.roommate.createRep

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.viewModels
import com.grouptheory.roommate.DataClasses.Rep
import com.grouptheory.roommate.R
import com.grouptheory.roommate.RoomMateApplication
import com.grouptheory.roommate.ui.main.MainViewModel

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "username"

/**
 * A simple [Fragment] subclass.
 * Use the [PostRepFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostRepFragment : Fragment() {
    // ViewModel to post reps to shared data
    // TODO: Good practice calls for its own view-model
    private val postRepViewModel: MainViewModel by viewModels {
        MainViewModel.MainViewModelFactory(
            (activity?.application as RoomMateApplication).repository
        )
    }

    // User to post post rep to
    private lateinit var username: String
    private var points: Int = 0
    private lateinit var descriptionTextView: TextView
    private lateinit var postButton : Button
    private lateinit var pointsBar: SeekBar
    private lateinit var pointDisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            username = it.getString(ARG_PARAM1).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_post_rep, container, false)

        // Get all of the view elements
        descriptionTextView = root.findViewById(R.id.editDescription)
        postButton = root.findViewById(R.id.postButton)
        pointsBar = root.findViewById(R.id.pointsSeekBar)
        pointDisplay = root.findViewById(R.id.pointsDisplay)
        pointDisplay.text = "Points: 0"

        pointsBar.setOnSeekBarChangeListener( object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                pointDisplay.text = "Points: $progress"
                points = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Intentionally blank
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Intentionally blank
            }

        })

        postButton.setOnClickListener {
            val newRep: Rep = Rep(description = descriptionTextView.text.toString(), points = points)
            Log.d("onClick listener", newRep.toString())
            Log.d("onClick listener", username)

            postRepViewModel.addNewRep(newRep, username)
            activity?.finish()
        }

        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param username Parameter 1.
         * @return A new instance of fragment PostRepFragment.
         */
        @JvmStatic
        fun newInstance(username: String) =
            PostRepFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, username)
                }
            }
    }
}