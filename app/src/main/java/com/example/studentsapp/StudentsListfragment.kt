package com.example.studentsapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.adapter.OnStudentClickListener
import com.example.studentsapp.adapter.StudentsAdapter
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student


class StudentsListfragment : Fragment()  {

    var students: MutableList<Student> = ArrayList()
    private lateinit var adapter: StudentsAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_student_list, container, false)

        students= Model.shared.students

        recyclerView = view.findViewById(R.id.StudentsRecyclerList)
        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        adapter = StudentsAdapter(students)

        adapter.listener= object : OnStudentClickListener {

            override fun onStudentClick(student: Student?) {
                Log.d("TAG", "Student clicked name: ${student?.name}")

                student?.let {
                    val action = StudentsListfragmentDirections
                        .actionStudentsListfragmentToStudentDetailsFragment(
                            it.name,
                            it.id,
                            it.phone,
                            it.address,
                            it.isChecked
                        )
                    Navigation.findNavController(view).navigate(action)
                }

            }
        }
        recyclerView.adapter = adapter

        return view
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

    private fun updateUI() {
        adapter.notifyDataSetChanged()
    }






}