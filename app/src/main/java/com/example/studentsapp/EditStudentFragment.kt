package com.example.studentsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.TextInputEditText


class EditStudentFragment : Fragment() {

    var nameEditText: TextInputEditText?= null
    var idEditText: TextInputEditText?= null
    var phoneEditText: TextInputEditText?= null
    var addressEditText: TextInputEditText?= null
    var checkBox: MaterialCheckBox?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.edit_student, container, false)

        //get the data from the intent
        val studentId = intent.getStringExtra("student_id") ?: ""
        val studentName = intent.getStringExtra("student_name") ?: ""
        val studentPhone = intent.getStringExtra("student_phone") ?: ""
        val studentAddress = intent.getStringExtra("student_address") ?: ""
        val studentIsChecked = intent.getBooleanExtra("student_isChecked", false)

        //get the fields from the layout
        nameEditText = view.findViewById(R.id.NameEditText)
        idEditText = view.findViewById(R.id.IDEditText)
        phoneEditText = view.findViewById(R.id.PhoneEditText)
        addressEditText = view.findViewById(R.id.AddressEditText)
        checkBox = view.findViewById(R.id.checkBox)






        return view
    }


}