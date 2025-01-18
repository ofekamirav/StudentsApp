package com.example.studentsapp.adapter

import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.R
import com.example.studentsapp.databinding.StudentListRowBinding
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student
import com.google.android.material.checkbox.MaterialCheckBox
import com.squareup.picasso.Picasso

//מנהל ומשייך את הנתונים עבור כל שורה ברשימה
class StudentViewHolder(
    private val binding: StudentListRowBinding,
    private val listener: OnStudentClickListener?
): RecyclerView.ViewHolder(binding.root) {


    private var student: Student? = null

    init {
        binding.checkBox.apply {
            setOnClickListener { view ->
                (tag as? Int)?.let { tag ->
                    student?.isChecked = (view as? MaterialCheckBox)?.isChecked ?: false
                    // עדכון הסטודנט ב-DB אחרי שינוי ה-checkbox
                    student?.let {
                        // לעדכן ב-Model כאן
                        Model.shared.updateStudent(it) {
                            Log.d("TAG", "Student updated in DB: ${student?.name}")
                        }
                    }
                }
            }
        }

            itemView.setOnClickListener {
                student?.let { listener?.onStudentClick(it) }
                Log.d("TAG", "Student clicked: ${student?.name}")
            }


        }

    //של שורה אחת מיפוי נתוני הסטודנט לרכיבי הUI
    fun bind(student: Student?, position: Int) {
        this.student = student
        binding.NameTextView.text = student?.name
        binding.IDTextView.text = student?.id
        binding.checkBox.tag = position // לשמירת המיקום

        binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            student?.isChecked = isChecked
            // עדכון הסטודנט ב-DB כשה-checkbox משתנה
            student?.let {
                Model.shared.updateStudent(it) {
                    Log.d("TAG", "Student's checkbox updated in DB")
                }
            }
            //load image to the row
            student?.avatarUrl?.let{ avatarUrl ->
                if (avatarUrl.isNotBlank()) {
                    Picasso.get()
                        .load(avatarUrl)
                        .resize(50,50)
                        .placeholder(R.drawable.student_icon)
                        .error(R.drawable.ic_launcher_foreground)
                        .into(binding.StudentPic)
                }
            }
        }
    }


}
