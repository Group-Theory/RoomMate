package com.grouptheory.roommate.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.grouptheory.roommate.R
import com.grouptheory.roommate.RoomMateApplication

class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels {
        MainViewModel.MainViewModelFactory((activity?.application as RoomMateApplication).repository)
    }
    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewModel.startFetchingData()
    }

}