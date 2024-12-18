package com.example.studentsapp


import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.TextInputEditText


class EditStudent: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.edit_student)

        //get the data from the intent
        val studentId = intent.getStringExtra("student_id")
        val studentName = intent.getStringExtra("student_name")
        val studentPhone = intent.getStringExtra("student_phone")
        val studentAddress = intent.getStringExtra("student_address")
        val studentIsChecked = intent.getBooleanExtra("student_isChecked", false)

        //get the fields from the layout
        var nameEditText: TextInputEditText= findViewById(R.id.NameEditText)
        var idEditText: TextInputEditText= findViewById(R.id.IDEditText)
        var phoneEditText: TextInputEditText= findViewById(R.id.PhoneEditText)
        var addressEditText: TextInputEditText= findViewById(R.id.AddressEditText)
        var checkBox: MaterialCheckBox= findViewById(R.id.checkBox)

        //get the buttons from the layout
        var saveButton: Button= findViewById(R.id.UpdateStudentBTN)
        var cancelButton: Button = findViewById(R.id.CancelBTN)

        //set the data to the fields
        nameEditText.setText(studentName)
        idEditText.setText(studentId)
        phoneEditText.setText(studentPhone)
        addressEditText.setText(studentAddress)
        checkBox.isChecked = studentIsChecked



        saveButton.setOnClickListener {
            val UpdateName= nameEditText.text.toString()
            val UpdateId= idEditText.text.toString()
            val UpdatePhone= phoneEditText.text.toString()
            val UpdateAddress= addressEditText.text.toString()
            val UpdateIsChecked= checkBox.isChecked

            //update the data in the Model list
            val index = Model.shared.students.indexOfFirst { it.id == studentId }
            if (index != -1) {
                Model.shared.students[index]=Student(UpdateName, UpdateId, UpdatePhone, UpdateAddress, UpdateIsChecked)
        }
            finish()
        }

        cancelButton.setOnClickListener {
            finish()
        }


    }

}