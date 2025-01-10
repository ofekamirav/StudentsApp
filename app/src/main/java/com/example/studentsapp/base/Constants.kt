package com.example.studentsapp.base

import com.example.studentsapp.model.Student

typealias StudentCallback = (List<Student>) -> Unit
typealias EmptyCallback = () -> Unit

object Constants {

    object Collections {
        const val STUDENTS = "students"
    }


}