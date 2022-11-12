package com.grouptheory.roommate.DataClasses

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rep (
    var id: Int = 0,
    val description: String = "",
    val dateAssigned: Long = 0,
    val dateDone: Long = 0, //set to 0 if not done
    val done: Int = 0, //same as before, but -1 if it is never done
    val points: Int = 0//negative if a complaint. Positive if a compliment
) :Parcelable
