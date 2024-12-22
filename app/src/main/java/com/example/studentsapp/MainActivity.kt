package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity: AppCompatActivity() {


    private var studentsListFrag: StudentsListfragment? = null
    private var NewStudentFrag : NewStudentFragment? = null

    private var addStudentButton: Button? = null
    private var inDisplayFragment: Fragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //set the fragments
        studentsListFrag= StudentsListfragment()
        NewStudentFrag = NewStudentFragment()

        //set the button
        addStudentButton = findViewById(R.id.AddStudentButton)


        addStudentButton?.setOnClickListener {
           displayFragment(NewStudentFrag!!)
        }


        displayFragment(studentsListFrag!!)

    }


    private fun displayFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.main_activity_fragment_place, fragment)//the place of fragment in the activity

            inDisplayFragment?.let { remove(it) }

            addToBackStack("TAG")
            commit()

        }
        inDisplayFragment = fragment
    }



}