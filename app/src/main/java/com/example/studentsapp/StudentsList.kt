package com.example.studentsapp

import android.content.Intent
import android.util.Log
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student
import com.google.android.material.checkbox.MaterialCheckBox


class StudentsList : AppCompatActivity() {

    var students: MutableList<Student> = ArrayList()
    private lateinit var adapter: StudentsAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        students= Model.shared.students

        recyclerView = findViewById(R.id.RecyclerList)
        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        adapter = StudentsAdapter(students)
        recyclerView.adapter = adapter

        val addStudentButton: Button = findViewById(R.id.AddStudentButton)
        addStudentButton.setOnClickListener {
            val intent = Intent(this, NewStudent::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

    private fun updateUI() {
        val adapter = StudentsAdapter(Model.shared.students)
        recyclerView.adapter = adapter
    }



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


    class StudentsAdapter(private val students: MutableList<Student>) : RecyclerView.Adapter<StudentsViewHolder>(){


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




}