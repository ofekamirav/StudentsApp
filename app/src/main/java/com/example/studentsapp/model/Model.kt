package com.example.studentsapp.model;

class Model private constructor() {

    val students: MutableList<Student> = ArrayList()

    companion object {
        val shared = Model()
    }


}
