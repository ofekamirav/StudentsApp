package com.example.studentsapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.R
import com.example.studentsapp.StudentDetails
import com.example.studentsapp.model.Student

class StudentsAdapter(private val students: MutableList<Student>) :  RecyclerView.Adapter<StudentsViewHolder>(){


        //אתחול של שורה ברשימה שלנו
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsViewHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(
                R.layout.student_list_row,
                parent,
                false
            )
            return StudentsViewHolder(view)
        }
        //כמה פריטים יופיעו ברשימה
        override fun getItemCount(): Int {
            return students.size
        }
        //הזרקת המידע הרלוונטי לאותה שורה שיצרנו
        override fun onBindViewHolder(holder: StudentsViewHolder, position: Int) {
            val student = students[position]
            holder.studentPic?.setImageResource(R.drawable.student_icon)
            holder.studentName?.text = student.name
            holder.studentID?.text = student.id

            holder.studentChecked.setOnCheckedChangeListener(null)
            holder.studentChecked.isChecked = student.isChecked
            holder.studentChecked.setOnCheckedChangeListener { _, isChecked ->
                student.isChecked = isChecked
            }


            //holder.studentChecked.tag = position

            //holder.studentChecked.setOnClickListener { view ->
              //  (view.tag as? Int)?.let { tag ->
                //    var student = students[tag]
                  //  student.isChecked = (view as? MaterialCheckBox)?.isChecked ?: false
              //  }
            //}


            //pass the data for StudentDetails Activity
            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context, StudentDetails::class.java)
                intent.putExtra("name", student.name)
                intent.putExtra("id", student.id)
                intent.putExtra("phone", student.phone)
                intent.putExtra("address", student.address)
                intent.putExtra("isChecked", student.isChecked)
                holder.itemView.context.startActivity(intent)
            }


        }

    }