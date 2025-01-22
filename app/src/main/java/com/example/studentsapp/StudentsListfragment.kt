package com.example.studentsapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.adapter.OnStudentCheckboxChangeListener
import com.example.studentsapp.adapter.OnStudentClickListener
import com.example.studentsapp.adapter.StudentsAdapter
import com.example.studentsapp.databinding.FragmentStudentListBinding
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student


class StudentsListfragment : Fragment()  {

    private var adapter: StudentsAdapter?= null
    private var recyclerView: RecyclerView?= null

    private var binding: FragmentStudentListBinding? = null
    private var viewModel: StudentsListViewModel?= null


    //to connect the view model
    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[StudentsListViewModel::class.java]
    }

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

        adapter = StudentsAdapter(viewModel?.students)

        adapter?.listener= object : OnStudentClickListener {

            override fun onStudentClick(student: Student?) {
                Log.d("TAG", "Student clicked name: ${student?.name}")

                student?.let {
                    val action = StudentsListfragmentDirections
                        .actionStudentsListfragmentToStudentDetailsFragment(it.id)
                    binding?.root?.let { it ->
                        Navigation.findNavController(it).navigate(action)
                    }
                }

            }
        }

        // Handle checkbox state change listener
        adapter?.setOnStudentCheckboxChangeListener(object : OnStudentCheckboxChangeListener {
            override fun onCheckboxChanged(student: Student, isChecked: Boolean) {
                student.isChecked = isChecked
                Model.shared.updateStudent(student) {
                    Log.d("TAG", "Student's checkbox updated in DB")
                    // Update ViewModel's student list
                    viewModel?.students = viewModel?.students?.map { s ->
                        if (s.id == student.id) student else s
                    }
                    adapter?.set(viewModel?.students)
                    adapter?.notifyDataSetChanged()
                }
            }
        })
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
            viewModel?.set(students = it)
            adapter?.set(it)
            adapter?.notifyDataSetChanged()
            binding?.progressBar?.visibility = View.GONE
        }
    }









}