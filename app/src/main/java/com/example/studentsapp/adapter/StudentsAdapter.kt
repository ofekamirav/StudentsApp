package com.example.studentsapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.R
import com.example.studentsapp.databinding.StudentListRowBinding
import com.example.studentsapp.model.Student


class StudentsAdapter(
    private var students: List<Student>?,
    var listener: OnStudentClickListener? = null
) :  RecyclerView.Adapter<StudentViewHolder>(){

    private var checkboxListener: OnStudentCheckboxChangeListener? = null

    fun set(students: List<Student>?){
        this.students = students
    }

    fun setOnStudentCheckboxChangeListener(listener: OnStudentCheckboxChangeListener) {
        this.checkboxListener = listener
    }

    //אתחול של שורה ברשימה שלנו
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = StudentListRowBinding.inflate(inflater, parent, false)

            return StudentViewHolder(binding, listener, checkboxListener)
        }
        //כמה פריטים יופיעו ברשימה
        override fun getItemCount(): Int {
            return students?.size ?: 0
        }
        //הזרקת המידע הרלוונטי לאותה שורה שיצרנו
        override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
            val student = students?.get(position)
            holder.bind(student, position)

        }

    }