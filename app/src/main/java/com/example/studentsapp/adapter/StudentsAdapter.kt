package com.example.studentsapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.R
import com.example.studentsapp.model.Student


class StudentsAdapter(
    private val students: MutableList<Student>,
    var listener: OnStudentClickListener? = null
) :  RecyclerView.Adapter<StudentViewHolder>(){

    //אתחול של שורה ברשימה שלנו
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflation = LayoutInflater.from(parent.context)
        val view: View = inflation.inflate(
                R.layout.student_list_row,
                parent,
                false
            )
         return StudentViewHolder(view, listener)
        }
        //כמה פריטים יופיעו ברשימה
        override fun getItemCount(): Int {
            return students.size
        }
        //הזרקת המידע הרלוונטי לאותה שורה שיצרנו
        override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
            val student = students[position]
           holder.bind(student, position)

        }

    }