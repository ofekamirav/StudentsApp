package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity.RESULT_OK
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.studentsapp.databinding.StudentDetailsBinding
import com.example.studentsapp.model.Model
import com.google.android.material.checkbox.MaterialCheckBox

class StudentDetailsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)//to not showing the add button in menu
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    //replace the menu with the new one
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.editstudent_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private var binding: StudentDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = StudentDetailsBinding.inflate(inflater, container, false)

        val studentId = arguments?.let { StudentDetailsFragmentArgs.fromBundle(it).studentId }

        if (studentId != null) {
            Model.shared.getStudentById(studentId) { studentList ->
                if (studentList.isNotEmpty()) {
                    val student = studentList[0]

                    binding?.nameValue?.text = student.name
                    binding?.IdValue?.text = student.id
                    binding?.phoneValue?.text = student.phone
                    binding?.addressValue?.text = student.address
                    binding?.checkedBox?.isChecked = student.isChecked

                }
            }

            binding?.DeleteStudentBtn?.setOnClickListener {
                Model.shared.getStudentById(studentId) { studentList ->
                    if (studentList.isNotEmpty()) {
                        val student = studentList[0]
                        Model.shared.deleteStudent(student) {
                            Log.d("StudentDetailsFragment", "Student deleted successfully!")
                        }
                        AlertDialog.Builder(requireContext())
                            .setTitle("Deleted")
                            .setMessage("Student deleted successfully!")
                            .setPositiveButton("OK") { dialog, _ ->
                                dialog.dismiss()
                                parentFragmentManager.popBackStack()
                            }
                            .show()
                    }
                }
            }


//        editStudentBtn?.setOnClickListener{
//            Navigation.findNavController(view).navigate(R.id.action_studentDetailsFragment_to_editStudentFragment)
//        }

        }
        return binding?.root
    }
}




