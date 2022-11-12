package com.grouptheory.roommate.ui.main

import android.text.LoginFilter.UsernameFilterGMail
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grouptheory.roommate.DataClasses.Rep
import com.grouptheory.roommate.DataClasses.User
import com.grouptheory.roommate.repository.FbRepository

class MainViewModel(private val mrepository: FbRepository): ViewModel() {
    private val repository = mrepository

    //make the mutable live data private to avoid errors in the future
    private val _roommatesLiveData = MutableLiveData<List<User>>()
    val roommatesLiveData : LiveData<List<User>> = _roommatesLiveData

    private val _userLiveData = MutableLiveData<User>()
    val userLiveData: LiveData<User> = _userLiveData

    fun startFetchingData() {
        repository.fetchHouseData(_roommatesLiveData)
        repository.fetchUserData(_userLiveData)
    }

    class MainViewModelFactory(private val repository: FbRepository) : ViewModelProvider.Factory {
        override fun <T: ViewModel> create(modelClass: Class<T>): T{
            if(modelClass.isAssignableFrom(MainViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(repository) as T
            }
            throw java.lang.IllegalArgumentException("Unknown ViewModel Class")
        }
    }

    fun updateUser(user: User){
        repository.updateUser(user)
    }

    fun insertUser(user: User){
        repository.addNewUser(user)
    }

    fun addNewRep(rep: Rep, userName: String){

    }
}