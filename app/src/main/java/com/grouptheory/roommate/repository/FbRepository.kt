package com.grouptheory.roommate.repository

import android.renderscript.Sampler.Value
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.grouptheory.roommate.DataClasses.User

class FbRepository {
    private val _username = "Joe"
    private val _houseName = "our_house"
    val database = Firebase.database

    val houseRef = database.getReference(_houseName)
    var userRef = houseRef.child(_username)

    lateinit var user: User

    init{ //create a blank user to start with
        user = User("blank user here", "blank PWHash here", score = 0)
    }

    //run this at the start/creation of the app to start fetching house and user data
    fun fetchHouseData(liveData: MutableLiveData<List<User>>) {
        houseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val users: List<User> = snapshot.children.map { roommateSnapshot ->
                    roommateSnapshot.getValue(User::class.java)!!
                }
                liveData.postValue(users)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("fetch House Data", "Error House Data Cancelled")
            }
        })
    }

    fun fetchUserData(liveData: MutableLiveData<User>) {
        userRef.addValueEventListener((object  : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user: User? = snapshot.getValue<User>()
                if(user != null) {
                    Log.d("Snapshot", user.userName)
                    liveData.postValue(user) //only post data if the user is not null
                } else {
                    Log.e("fetch User Data", "Error User Data Is Null")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("fetch User Data", "Error User Data Cancelled")
            }
        }))
    }
}