package com.example.studentsapp

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.studentsapp.databinding.EditStudentBinding
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditStudentFragment : Fragment() {

    private var binding: EditStudentBinding? = null
    private var studentId: String? = null
    private var cameraLauncher: ActivityResultLauncher<Void?>? = null
    private var didSetProfileImage: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        cameraLauncher =
            registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
                if (bitmap != null) {
                    binding?.StudentPic?.setImageBitmap(bitmap)
                    didSetProfileImage = true
                }
            }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = EditStudentBinding.inflate(inflater, container, false)

        binding?.addPhotoButton?.setOnClickListener {
            cameraLauncher?.launch(null)
        }


        //get the student id from the arguments
        studentId = arguments?.let { EditStudentFragmentArgs.fromBundle(it).studentId }
        Log.d("TAG", "EditStudentFragment: Recieved studentId: $studentId")


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

                        // Load student image with Picasso
                        if (student.avatarUrl.isNotEmpty()) {
                            binding?.root?.context?.let {
                                binding?.StudentPic?.let { it1 ->
                                    Glide.with(it)
                                        .load(student.avatarUrl)
                                        .placeholder(R.drawable.student_icon)
                                        .circleCrop()
                                        .into(it1)
                                }
                            }
                        }
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
        binding?.progressBar?.visibility = View.VISIBLE
        val name = binding?.NameEditText?.text.toString()
        val id = binding?.IDEditText?.text.toString()
        val address = binding?.AddressEditText?.text.toString()
        val phone = binding?.PhoneEditText?.text.toString()
        val isChecked = binding?.checkBox?.isChecked ?: false
        val birthDate = binding?.BirthDateEditText?.text.toString()
        val birthTime = binding?.BirthTimeEditText?.text.toString()
        val image = binding?.StudentPic?.drawable as? BitmapDrawable
        val bitmap = image?.bitmap

        //handle empty fields
        if (name.isEmpty() || id.isEmpty() || phone.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all required fields.", Toast.LENGTH_SHORT)
                .show()
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
            if (didSetProfileImage) {
                // Update the student with the new image
                updateStudentWithImage(updatedStudent, bitmap)
            } else {
                // Update the student without the new image
                updateStudentWithoutImage(updatedStudent)
            }
        }

    }
    private fun updateStudentWithImage(student: Student, bitmap: Bitmap?) {
        Model.shared.updateStudentwithImage(student, bitmap) {
            // חזרה ל-UI thread באמצעות lifecycleScope
            lifecycleScope.launch(Dispatchers.Main) {
                binding?.progressBar?.visibility = View.GONE
                showSuccessDialog()
            }
        }
    }

    private fun updateStudentWithoutImage(student: Student) {
        Model.shared.updateStudent(student) {
            // חזרה ל-UI thread באמצעות lifecycleScope
            lifecycleScope.launch(Dispatchers.Main) {
                binding?.progressBar?.visibility = View.GONE
                showSuccessDialog()
            }
        }
    }

    private fun showSuccessDialog() {
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

