package com.example.studentsapp.adapter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.studentsapp.R
import com.example.studentsapp.databinding.StudentListRowBinding
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student
import com.google.android.material.checkbox.MaterialCheckBox

//מנהל ומשייך את הנתונים עבור כל שורה ברשימה
class StudentViewHolder(
    private val binding: StudentListRowBinding,
    private val listener: OnStudentClickListener?,
    private val checkboxListener: OnStudentCheckboxChangeListener?
): RecyclerView.ViewHolder(binding.root) {

    private var student: Student? = null

    init {

        // Handle checkbox clicks
        binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            student?.let {
                it.isChecked = isChecked
                checkboxListener?.onCheckboxChanged(it, isChecked)
            }
        }

        // Handle item clicks
        itemView.setOnClickListener {
            student?.let { listener?.onStudentClick(it) }
        }
    }

    //של שורה אחת מיפוי נתוני הסטודנט לרכיבי הUI
    fun bind(student: Student?, position: Int) {
        this.student = student
        binding.NameTextView.text = student?.name
        binding.IDTextView.text = student?.id
        binding.checkBox.tag = position // לשמירת המיקום

        // Initialize checkbox state
        binding.checkBox.isChecked = student?.isChecked ?: false

        //load image to the row
        student?.avatarUrl?.let { avatarUrl ->
            Log.d("TAG", "Loading image for student: ${student.name}, avatarUrl: $avatarUrl")
            if (avatarUrl.isNotBlank()) {
                Glide.with(binding.root.context)
                    .load(avatarUrl)
                    .placeholder(R.drawable.student_icon)
                    .circleCrop()
                    .into(binding.StudentPic)
            } else {
                binding.StudentPic.setImageResource(R.drawable.student_icon)
            }
        }

    }
}


