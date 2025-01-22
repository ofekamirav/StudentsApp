package com.example.studentsapp

import androidx.lifecycle.ViewModel
import com.example.studentsapp.model.Student

class StudentsListViewModel: ViewModel() {

    private var _students: List<Student> = ArrayList()
    var students: List<Student>?
        get() = _students
        set(value) {
            _students = value ?: ArrayList()
        }

    fun set(students: List<Student>?) {
        this.students = students
    }





}