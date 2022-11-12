package com.grouptheory.roommate.DataClasses
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val userName: String = "roommate",
    val pWHash: String = "",
    var complaints: List<Complaint> = emptyList(),
    var score: Long = 0,
    var chores: List<Chore> = emptyList()
) : Parcelable
