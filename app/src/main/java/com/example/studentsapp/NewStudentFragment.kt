package com.example.studentsapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.TextInputEditText

class NewStudentFragment : Fragment() {

    var nameEditText: TextInputEditText?=null
    var idEditText: TextInputEditText?=null
    var phoneEditText: TextInputEditText?=null
    var addressEditText: TextInputEditText?=null
    var checkBox: MaterialCheckBox?=null
    var addStudentButton: Button ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)//to not showing the add button in menu
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.new_student, container, false)

        setUp(view)


        addStudentButton?.setOnClickListener(::onAddStudentClicked)

        return view
    }

    //erase the add button from the menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setUp(view: View?){
        nameEditText= view?.findViewById(R.id.NameEditText)
        idEditText = view?.findViewById(R.id.IDEditText)
        phoneEditText = view?.findViewById(R.id.PhoneEditText)
        addressEditText = view?.findViewById(R.id.AddressEditText)
        checkBox = view?.findViewById(R.id.checkBox)
        addStudentButton = view?.findViewById(R.id.AddStudentButton)

    }

    private fun onAddStudentClicked(view: View?){
        val student = Student(
            nameEditText?.text.toString(),
            idEditText?.text.toString(),
            phoneEditText?.text.toString(),
            addressEditText?.text.toString(),
            checkBox?.isChecked?:false
        )

        Model.shared.students.add(student)
        Log.d("NewStudentFragment", "Student added: $student")
        Log.d("NewStudentFragment", "Students in Model: ${Model.shared.students}")

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
