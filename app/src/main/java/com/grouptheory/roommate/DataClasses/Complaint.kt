package com.grouptheory.roommate.DataClasses

data class Complaint(
    val id: String = "", //just used as a primary key
    val description: String = "",
    val dueDate: Long = 0, //epoch time baby. When it is assigned
    var done: Int = 0, //0=not done >=1 means done. Not sure if this is necessary?
    var points: Int = 0, //points deducted because of this
    var extra: String = "here"//in case we need to store more arguments. Just put it in a string and parse it
)
