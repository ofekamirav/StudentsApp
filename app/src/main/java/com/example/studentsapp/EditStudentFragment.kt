package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity.RESULT_OK
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.TextInputEditText


class EditStudentFragment : Fragment() {

    var nameEditText: TextInputEditText?= null
    var idEditText: TextInputEditText?= null
    var phoneEditText: TextInputEditText?= null
    var addressEditText: TextInputEditText?= null
    var checkBox: MaterialCheckBox?= null
    var cancelButton: Button?= null
    var saveButton: Button?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.edit_student, container, false)

//        //get the data from the intent
//        val studentId = intent.getStringExtra("student_id") ?: ""
//        val studentName = intent.getStringExtra("student_name") ?: ""
//        val studentPhone = intent.getStringExtra("student_phone") ?: ""
//        val studentAddress = intent.getStringExtra("student_address") ?: ""
//        val studentIsChecked = intent.getBooleanExtra("student_isChecked", false)

        setUp(view)

//        cancelButton?.setOnClickListener()
//        saveButton?.setOnClickListener()



        return view
    }

    private fun setUp(view: View?){
        //get the fields from the layout
        nameEditText = view?.findViewById(R.id.NameEditText)
        idEditText = view?.findViewById(R.id.IDEditText)
        phoneEditText = view?.findViewById(R.id.PhoneEditText)
        addressEditText = view?.findViewById(R.id.AddressEditText)
        checkBox = view?.findViewById(R.id.checkBox)

        //get the Buttons from the layout
        saveButton = view?.findViewById(R.id.UpdateStudentBTN)
        cancelButton = view?.findViewById(R.id.CancelBTN)


    }

//    //set the data to the fields
//    nameEditText?.setText(studentName)
//    idEditText?.setText(studentId)
//    phoneEditText?.setText(studentPhone)
//    addressEditText?.setText(studentAddress)
//    checkBox?.isChecked = studentIsChecked
//
//
//
//    saveButton.setOnClickListener {
//        val UpdateName= nameEditText.text.toString()
//        val UpdateId= idEditText.text.toString()
//        val UpdatePhone= phoneEditText.text.toString()
//        val UpdateAddress= addressEditText.text.toString()
//        val UpdateIsChecked= checkBox.isChecked
//
//        //update the data in the Model list
//        val index = Model.shared.students.indexOfFirst { it.id == studentId }
//        if (index != -1) {
//            Model.shared.students[index]=
//                Student(UpdateName, UpdateId, UpdatePhone, UpdateAddress, UpdateIsChecked)
//        }
//        // send Updated data to the StudentDetails Activity
//        val resultIntent = Intent()
//        resultIntent.putExtra("student_name", UpdateName)
//        resultIntent.putExtra("student_id", UpdateId)
//        resultIntent.putExtra("student_phone", UpdatePhone)
//        resultIntent.putExtra("student_address", UpdateAddress)
//        resultIntent.putExtra("student_isChecked", UpdateIsChecked)
//        setResult(RESULT_OK, resultIntent)
//
//        finish()
//    }
//
//    cancelButton.setOnClickListener {
//        finish()
//    }



}