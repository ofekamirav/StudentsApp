package com.example.studentsapp.adapter

import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.R
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student
import com.google.android.material.checkbox.MaterialCheckBox

//מנהל ומשייך את הנתונים עבור כל שורה ברשימה
class StudentViewHolder(
    itemView: View,
    private val listener: OnStudentClickListener?
): RecyclerView.ViewHolder(itemView) {


    private var student: Student? = null

    var studentPic: ImageView? = itemView.findViewById(R.id.StudentPic)
    var studentName: TextView? = itemView.findViewById(R.id.NameTextView)
    var studentID: TextView? = itemView.findViewById(R.id.IDTextView)
    var studentChecked: MaterialCheckBox? = itemView.findViewById(R.id.checkBox)

    init {
            studentPic?.setImageResource(R.drawable.student_icon)
            studentName= itemView.findViewById(R.id.NameTextView)
            studentID= itemView.findViewById(R.id.IDTextView)
            studentChecked= itemView.findViewById(R.id.checkBox)

        studentChecked?.apply {
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

    //מיפוי נתוני הסטודנט לרכיבי הUI
    fun bind(student: Student?, position: Int) {
        this.student = student
        studentPic?.setImageResource(R.drawable.student_icon)
        studentName?.text = student?.name
        studentID?.text = student?.id
        studentChecked?.isChecked = student?.isChecked ?: false
        studentChecked?.tag = position // לשמירת המיקום

        studentChecked?.setOnCheckedChangeListener { _, isChecked ->
            student?.isChecked = isChecked
            // עדכון הסטודנט ב-DB כשה-checkbox משתנה
            student?.let {
                Model.shared.updateStudent(it) {
                    Log.d("TAG", "Student's checkbox updated in DB")
                }
            }
        }
    }


}
