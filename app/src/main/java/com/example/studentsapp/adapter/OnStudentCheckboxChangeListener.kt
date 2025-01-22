package com.example.studentsapp.adapter

import com.example.studentsapp.model.Student

interface OnStudentCheckboxChangeListener {
    fun onCheckboxChanged(student: Student, isChecked: Boolean)
}
