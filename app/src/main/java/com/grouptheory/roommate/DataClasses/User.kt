package com.grouptheory.roommate.DataClasses
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val userName: String = "roommate",
    val pWHash: String = "",
    var score: Long = 0,
    var reps: List<Rep> = emptyList()
) : Parcelable
