package com.example.studentsapp.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.studentsapp.model.Student


@Dao
interface StudentDao {

    @Query("SELECT * FROM student")
    fun GetAllStudents(): List<Student>

    @Query("SELECT * FROM student WHERE id = :id")
    fun GetStudentById(id: String): Student

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun InsertStudent(vararg students: Student)

    @Delete
    fun DeleteStudent(student: Student)

    @Update
    fun UpdateStudent(student: Student)
}