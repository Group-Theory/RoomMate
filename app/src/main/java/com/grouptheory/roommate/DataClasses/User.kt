package com.grouptheory.roommate.DataClasses

data class User(
    val userName: String = "roommate",
    val pWHash: String = "",
    var score: Long = 0,
    var reps: List<Rep> = emptyList()
) : Parcelable
