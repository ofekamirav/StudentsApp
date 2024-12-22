package com.example.studentsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.adapter.StudentsAdapter
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student


class StudentsListfragment : Fragment() {

    var students: MutableList<Student> = ArrayList()
    private lateinit var adapter: StudentsAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.students_list_recycler_view, container, false)

        students= Model.shared.students

        recyclerView = view.findViewById(R.id.StudentsRecyclerList)
        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        adapter = StudentsAdapter(students)

        recyclerView.adapter = adapter

        return view
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

    private fun updateUI() {
        val adapter = StudentsAdapter(Model.shared.students)
        recyclerView.adapter = adapter
    }






}