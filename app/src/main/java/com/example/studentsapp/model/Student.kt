package com.example.studentsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(

    val name: String = "",
    @PrimaryKey val id: String = "",
    val phone: String = "",
    val address: String = "",
    var isChecked: Boolean = false,
    val BirthDate: String = "",
    val BirthTime: String = ""

)