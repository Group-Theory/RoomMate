package com.grouptheory.roommate.DataClasses

data class Chore (
    val id: String = "",
    val description: String = "",
    val dateAssigned: Long = 0,
    val dateDone: Long = 0, //set to 0 if not done
    val done: Int = 0, //same as before, but -1 if it is never done
    val points: Int = 0//how many points to raise their score if they don't do something. these points should be assigned whenever the chore is done
)