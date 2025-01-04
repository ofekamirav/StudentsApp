package com.example.studentsapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.adapter.OnStudentClickListener
import com.example.studentsapp.adapter.StudentsAdapter
import com.example.studentsapp.databinding.FragmentStudentListBinding
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student


class StudentsListfragment : Fragment()  {

    var students: List<Student> = ArrayList()
    private var adapter: StudentsAdapter?= null
    private var recyclerView: RecyclerView?= null
    var progressBar: ProgressBar? = null

    private var binding: FragmentStudentListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //easiest way to inflate the layout and working with xml views
        binding= FragmentStudentListBinding.inflate(inflater, container, false)


        recyclerView = binding?.StudentsRecyclerList
        recyclerView?.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(context)
        recyclerView?.layoutManager = layoutManager

        adapter = StudentsAdapter(students)

        adapter?.listener= object : OnStudentClickListener {

            override fun onStudentClick(student: Student?) {
                Log.d("TAG", "Student clicked name: ${student?.name}")

                student?.let {
                    val action = StudentsListfragmentDirections
                        .actionStudentsListfragmentToStudentDetailsFragment(it.id)
                    binding?.root?.let {
                        Navigation.findNavController(it).navigate(action)
                    }
                }

            }
        }
        recyclerView?.adapter = adapter

        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        GetAllStudents()
    }

    //relevant only for fragments because the binding destroy itself in activity
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun GetAllStudents() {
        //to add the progress bar when we get the data with outside thread
        binding?.progressBar?.visibility = View.VISIBLE

        Model.shared.getAllStudents {
            this.students = it
            adapter?.set(students)
            adapter?.notifyDataSetChanged() //notify the adapter to update the data

            binding?.progressBar?.visibility = View.GONE //end the progress bar
        }
    }









}