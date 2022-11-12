package com.grouptheory.roommate.DataClasses

data class User(
    val userName: String = "roommate",
    val pWHash: String = "",
    var complaints: List<Complaint> = emptyList(),
    var score: Long = 0,
    var chores: List<Chore> = emptyList()
)
