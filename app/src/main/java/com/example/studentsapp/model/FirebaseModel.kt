package com.example.studentsapp.model

import com.example.studentsapp.base.Constants.Collections.STUDENTS
import com.example.studentsapp.base.EmptyCallback
import com.example.studentsapp.base.StudentCallback
import com.google.firebase.firestore.firestoreSettings
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.memoryCacheSettings
import com.google.firebase.ktx.Firebase

class FirebaseModel {

    private val database = Firebase.firestore

    // Configure Firebase settings that not create ROOM local cache automatic
    init{
        val settings = firestoreSettings {
            setLocalCacheSettings(memoryCacheSettings {  })
        }
        database.firestoreSettings = settings
    }

    fun getAllStudents(callback: StudentCallback) {
        callback(listOf())
        database.collection(STUDENTS).get().addOnCompleteListener {
            when(it.isSuccessful){//CHECK IF IT ADDED SUCCESSFULLY
                true -> {
                    val students : MutableList<Student> = mutableListOf() //CREATE EMPTY LIST
                    for(json in it.result){//set all the students to the list
                        students.add(Student.fromJson(json.data))
                    }
                    callback(students)
                }
                false -> callback(listOf())

            }
        }
    }

    fun addStudent(student: Student, callback: EmptyCallback) {
        database.collection(STUDENTS).document(student.id).set(student.json)
            .addOnSuccessListener {
                callback()
            }
    }

    fun deleteStudent(student: Student, callback: EmptyCallback) {
        database.collection(STUDENTS).document(student.id).delete()
            .addOnSuccessListener {
                callback()
            }
    }
    fun getStudentById(id: String, callback: StudentCallback) {
        database.collection(STUDENTS).document(id).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val student = Student.fromJson(document.data ?: hashMapOf())
                    callback(listOf(student))
                } else {
                    callback(listOf()) // No student found
                }
            }
            .addOnFailureListener {
                callback(listOf()) // Error
            }

    }
    fun updateStudent(student: Student, callback: EmptyCallback) {
        database.collection(STUDENTS).document(student.id).set(student.json)
            .addOnSuccessListener {
                callback()
            }
            .addOnFailureListener {
                it.printStackTrace()
            }

    }
}