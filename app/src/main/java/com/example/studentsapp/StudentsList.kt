package com.example.studentsapp

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
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

class StudentsList : AppCompatActivity() {

    var students: MutableList<Student> = ArrayList()

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

        val recyclerView: RecyclerView = findViewById(R.id.RecyclerList)
        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val adapter = StudentsAdapter(students)
        recyclerView.adapter = adapter

    }

    class StudentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val studentPic: ImageView = itemView.findViewById(R.id.StudentPic)


    }


    class StudentsAdapter(private val students: List<Student>) : RecyclerView.Adapter<StudentsViewHolder>(){



        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsViewHolder {
            TODO("Not yet implemented")
        }

        override fun getItemCount(): Int {
            TODO("Not yet implemented")
        }

        override fun onBindViewHolder(holder: StudentsViewHolder, position: Int) {
            TODO("Not yet implemented")
        }

    }






}