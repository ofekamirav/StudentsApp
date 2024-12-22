package com.example.studentsapp.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.R
import com.google.android.material.checkbox.MaterialCheckBox

class StudentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var studentPic: ImageView? = itemView.findViewById(R.id.StudentPic)
        var studentName: TextView? = itemView.findViewById(R.id.NameTextView)
        var studentID: TextView? = itemView.findViewById(R.id.IDTextView)
        var studentChecked: MaterialCheckBox = itemView.findViewById(R.id.checkBox)
        init {
            studentPic?.setImageResource(R.drawable.student_icon)
            studentName= itemView.findViewById(R.id.NameTextView)
            studentID= itemView.findViewById(R.id.IDTextView)
            studentChecked= itemView.findViewById(R.id.checkBox)
        }

    }
