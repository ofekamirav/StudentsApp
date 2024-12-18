package com.example.studentsapp.model;

class Model private constructor() {

    val students: MutableList<Student> = ArrayList()

    companion object {
        val shared = Model()
    }

    fun deleteStudent(id: String) {
        students.removeIf { it.id == id }
    }


}
