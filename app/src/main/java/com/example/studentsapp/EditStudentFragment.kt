package com.example.studentsapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.studentsapp.databinding.EditStudentBinding
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditStudentFragment : Fragment() {

    private var binding: EditStudentBinding? = null
    private var studentId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EditStudentBinding.inflate(inflater, container, false)

        //get the student id from the arguments
        studentId = arguments?.let { EditStudentFragmentArgs.fromBundle(it).studentId }
        Log.d("TAG", "Recieved studentId: $studentId")


        //get the student by id
        studentId?.let { id ->
            lifecycleScope.launch {
                Model.shared.getStudentById(id) { studentList ->
                    if (studentList.isNotEmpty()) {
                        val student = studentList[0]
                        binding?.NameEditText?.setText(student.name)
                        binding?.IDEditText?.setText(student.id)
                        binding?.AddressEditText?.setText(student.address)
                        binding?.PhoneEditText?.setText(student.phone)
                        binding?.checkBox?.isChecked = student.isChecked
                        binding?.BirthDateEditText?.setText(student.BirthDate)
                        binding?.BirthTimeEditText?.setText(student.BirthTime)
                    }
                }
            }
        }

        binding?.UpdateStudentBTN?.setOnClickListener(::onUpdateStudentClicked)

        binding?.CancelBTN?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun onUpdateStudentClicked(view: View) {
        val name = binding?.NameEditText?.text.toString()
        val id = binding?.IDEditText?.text.toString()
        val address = binding?.AddressEditText?.text.toString()
        val phone = binding?.PhoneEditText?.text.toString()
        val isChecked = binding?.checkBox?.isChecked ?: false
        val birthDate = binding?.BirthDateEditText?.text.toString()
        val birthTime = binding?.BirthTimeEditText?.text.toString()

        //handle empty fields
        if (name.isEmpty() || id.isEmpty() || phone.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all required fields.", Toast.LENGTH_SHORT).show()
            return
        }

        val updatedStudent = Student(
            name = name,
            id = id,
            address = address,
            phone = phone,
            isChecked = isChecked,
            BirthDate = birthDate,
            BirthTime = birthTime
        )


        lifecycleScope.launch(Dispatchers.IO) {
            Log.d("TAG", "Updating student: $updatedStudent")
            Model.shared.updateStudent(updatedStudent) {
                Log.d("TAG", "Student updated successfully!")
            }
            withContext(Dispatchers.Main) {
                binding?.progressBar?.visibility = View.VISIBLE

                binding?.progressBar?.visibility = View.GONE
                AlertDialog.Builder(requireContext())
                    .setTitle("Saved")
                    .setMessage("Student updated successfully!")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                        parentFragmentManager.popBackStack()
                    }
                    .show()
            }
            }
        }
    }

