package com.example.studentsapp

import android.os.Bundle
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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
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

        val student = Student(nameEditText.text.toString(), idEditText.text.toString(), phoneEditText.text.toString(), addressEditText.text.toString(), checkBox.isChecked)

        addStudentButton.setOnClickListener {
            Model.shared.students.add(student)
            finish()
        }

        cancelButton.setOnClickListener {
            finish()
        }

    }
}