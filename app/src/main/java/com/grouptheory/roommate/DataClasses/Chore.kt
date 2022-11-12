package com.grouptheory.roommate.DataClasses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Chore (
    var id: Int = 0,
    val description: String = "",
    val dateAssigned: Long = 0,
    val dateDone: Long = 0, //set to 0 if not done
    val done: Int = 0, //same as before, but -1 if it is never done
    val points: Int = 0//how many points to raise their score if they don't do something. these points should be assigned whenever the chore is done
) :Parcelable