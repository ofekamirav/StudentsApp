package com.example.studentsapp.model;

import android.os.Looper
import androidx.core.os.HandlerCompat
import com.example.studentsapp.base.EmptyCallback
import com.example.studentsapp.base.StudentCallback
import com.example.studentsapp.model.dao.AppLocalDB
import com.example.studentsapp.model.dao.AppLocalDBRepository
import java.util.concurrent.Executors


class Model private constructor() {

    private val database: AppLocalDBRepository = AppLocalDB().database
    private val executor = Executors.newSingleThreadExecutor()//get out to outside thread
    private val mainHandler = HandlerCompat.createAsync(Looper.getMainLooper())//go back to main thread
    private val firebaseModel = FirebaseModel()

    companion object {
        val shared = Model()
    }

    fun getAllStudents(callback:StudentCallback) {
        firebaseModel.getAllStudents(callback)
//        executor.execute {
//            val students = database.studentDao().GetAllStudents()
//            Thread.sleep(2500)//to add a progress indicator
//            mainHandler.post {
//                callback(students)
//            }
//
//        }
    }

    fun addStudent(student: Student, callback: EmptyCallback) {
        firebaseModel.addStudent(student, callback)
        //executor.execute {
//            database.studentDao().InsertStudent(student)
//            Thread.sleep(2500)//to add a progress indicator, we add this in the layout
//            mainHandler.post {
//                callback()
//            }
//        }

    }

    fun deleteStudent(student: Student, callback: EmptyCallback) {
        firebaseModel.deleteStudent(student, callback)
//        executor.execute {
//            database.studentDao().DeleteStudent(student)
//            mainHandler.post {
//                callback()
//            }
//        }
    }
    fun getStudentById(id: String, callback: StudentCallback) {
        firebaseModel.getStudentById(id, callback)
//        executor.execute {
//            val student = database.studentDao().GetStudentById(id)
//            mainHandler.post {
//                callback(listOf(student))
//            }
//        }
    }

    fun updateStudent(student: Student, callback: EmptyCallback) {
        firebaseModel.updateStudent(student, callback)
//        executor.execute {
//            database.studentDao().UpdateStudent(student)
//            mainHandler.post {
//                callback()
//            }
//        }
    }

}
