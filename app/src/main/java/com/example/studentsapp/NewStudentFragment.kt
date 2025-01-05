package com.example.studentsapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.studentsapp.databinding.NewStudentBinding
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student


class NewStudentFragment : Fragment() {

    private var binding: NewStudentBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)//to not showing the add button in menu
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= NewStudentBinding.inflate(inflater,container,false)

        binding?.AddStudentButton?.setOnClickListener(::onAddStudentClicked)

        return binding?.root
    }

    //erase the add button from the menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }


    private fun onAddStudentClicked(view: View){
        binding?.progressBar?.visibility=View.VISIBLE
        val student = Student(
            name=binding?.NameEditText?.text.toString(),
            id=binding?.IDEditText?.text.toString(),
            address = binding?.AddressEditText?.text.toString(),
            phone = binding?.PhoneEditText?.text.toString(),
            isChecked = binding?.checkBox?.isChecked ?: false
        )

        Model.shared.addStudent(student){
            binding?.progressBar?.visibility=View.GONE
            Log.d("TAG", "Student added successfully!")
        }

        AlertDialog.Builder(requireContext())
            .setTitle("Saved")
            .setMessage("Student added successfully!")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                parentFragmentManager.popBackStack()
            }
            .show()
    }

}
