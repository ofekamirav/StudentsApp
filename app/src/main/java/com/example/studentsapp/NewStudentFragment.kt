package com.example.studentsapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.TextInputEditText

class NewStudentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.new_student, container, false)


        val nameEditText: TextInputEditText = view.findViewById(R.id.NameEditText)
        val idEditText: TextInputEditText = view.findViewById(R.id.IDEditText)
        val phoneEditText: TextInputEditText = view.findViewById(R.id.PhoneEditText)
        val addressEditText: TextInputEditText = view.findViewById(R.id.AddressEditText)
        val checkBox: MaterialCheckBox = view.findViewById(R.id.checkBox)


        val addStudentButton: Button = view.findViewById(R.id.AddStudentButton)
        val cancelButton: Button = view.findViewById(R.id.CancelButton)


        addStudentButton.setOnClickListener {
            val student = Student(
                nameEditText.text.toString(),
                idEditText.text.toString(),
                phoneEditText.text.toString(),
                addressEditText.text.toString(),
                checkBox.isChecked
            )

            Model.shared.students.add(student)
            Log.d("NewStudentFragment", "Student added: $student")
            Log.d("NewStudentFragment", "Students in Model: ${Model.shared.students}")

            closeFragment()
        }

        cancelButton.setOnClickListener {

            closeFragment()
        }

        return view
    }

    private fun closeFragment() {
        parentFragmentManager.popBackStack()
    }
}
