package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.model.Model
import com.google.android.material.checkbox.MaterialCheckBox


class StudentDetails : AppCompatActivity() {

    var nameValue: TextView?= null
    var IdValue: TextView?= null
    var phoneValue: TextView?= null
    var addressValue: TextView?= null
    var checkedBox: MaterialCheckBox?= null


   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.student_details)

       //get the data from the intent
       val name = intent.getStringExtra("name")
       val id = intent.getStringExtra("id")
       val phone = intent.getStringExtra("phone")
       val address = intent.getStringExtra("address")
       val isChecked = intent.getBooleanExtra("isChecked", false)


       //get the fields from the layout
       nameValue = findViewById(R.id.nameValue)
       IdValue = findViewById(R.id.IdValue)
       phoneValue = findViewById(R.id.phoneValue)
       addressValue = findViewById(R.id.addressValue)
       checkedBox = findViewById(R.id.checkedBox)

       //set the data to the fields
       nameValue?.text = name
       IdValue?.text = id
       phoneValue?.text = phone
       addressValue?.text = address
       checkedBox?.isChecked = isChecked

       //get the buttons from the layout
       var backButton: Button = findViewById(R.id.backButton)
       var editStudentBtn: Button = findViewById(R.id.EditStudentBtn)
       var deleteStudentBtn: Button = findViewById(R.id.DeleteStudentBtn)

       //set the click listeners for the buttons
       backButton.setOnClickListener {
           finish()
       }
       deleteStudentBtn.setOnClickListener {
           val studentId=id
           if(studentId!=null){
               Model.shared.deleteStudent(studentId)
               finish()
           }
       }

       editStudentBtn.setOnClickListener {
           val intent= Intent(this,EditStudent::class.java)

           //send the data to EditStudentActivity
           intent.putExtra("name", name)
           intent.putExtra("id", id)
           intent.putExtra("phone", phone)
           intent.putExtra("address", address)
           intent.putExtra("isChecked", isChecked)

           startActivityForResult(intent, 100)
       }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == RESULT_OK) {
            //get the update data after edit student
            val updatedName = data?.getStringExtra("student_name")
            val updatedPhone = data?.getStringExtra("student_phone")
            val updatedAddress = data?.getStringExtra("student_address")
            val updatedIsChecked = data?.getBooleanExtra("student_isChecked", false)

            //update the fields with the new data
            nameValue?.text = updatedName
            phoneValue?.text = updatedPhone
            addressValue?.text = updatedAddress
            checkedBox?.isChecked = updatedIsChecked ?: false
        }
    }


}
