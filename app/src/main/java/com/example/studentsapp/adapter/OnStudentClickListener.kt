package com.example.studentsapp.adapter
import com.example.studentsapp.model.Student



interface OnStudentClickListener {
    fun onStudentClick(position: Int)
    fun onStudentClick(student: Student?)
}

