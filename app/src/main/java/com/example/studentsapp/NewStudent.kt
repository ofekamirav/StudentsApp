package com.example.studentsapp

import android.util.Log
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.TextInputEditText


class NewStudent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.new_student)

        val rootView = findViewById<View>(android.R.id.content)

        ViewCompat.setOnApplyWindowInsetsListener(rootView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nameEditText: TextInputEditText = findViewById(R.id.NameEditText)
        val idEditText: TextInputEditText = findViewById(R.id.IDEditText)
        val phoneEditText: TextInputEditText = findViewById(R.id.PhoneEditText)
        val addressEditText: TextInputEditText = findViewById(R.id.AddressEditText)
        val checkBox: MaterialCheckBox = findViewById(R.id.checkBox)

        val addStudentButton: Button = findViewById(R.id.AddStudentButton)
        val cancelButton: Button = findViewById(R.id.CancelButton)

        addStudentButton.setOnClickListener {
            val student = Student(
                nameEditText.text.toString(),
                idEditText.text.toString(),
                phoneEditText.text.toString(),
                addressEditText.text.toString(),
                checkBox.isChecked
            )
            Model.shared.students.add(student)
            Log.d("NewStudent", "Student added: $student")
            Log.d("NewStudent", "Students in Model: ${Model.shared.students}")
            finish()
        }

        cancelButton.setOnClickListener {
            finish()
        }

    }
}