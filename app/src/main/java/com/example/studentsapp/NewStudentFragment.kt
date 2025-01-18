package com.example.studentsapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.studentsapp.databinding.NewStudentBinding
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.drawable.BitmapDrawable
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import java.util.Calendar


class NewStudentFragment : Fragment() {

    private var binding: NewStudentBinding?=null
    private var calendar: Calendar = Calendar.getInstance()
    private var cameraLauncher: ActivityResultLauncher<Void?>?=null
    private var didSetprofileImage: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)//to not showing the add button in menu
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= NewStudentBinding.inflate(inflater,container,false)

        cameraLauncher = registerForActivityResult(ActivityResultContracts.TakePicturePreview()){ bitmap ->
            binding?.StudentPic?.setImageBitmap(bitmap)
            didSetprofileImage = true
        }
        binding?.addPhotoButton?.setOnClickListener {
            //open camera launcher
            cameraLauncher?.launch(null)
        }


        binding?.AddStudentButton?.setOnClickListener(::onAddStudentClicked)

        binding?.BirthDateEditText?.setOnClickListener(::onBirthDateClicked)

        binding?.BirthTimeEditText?.setOnClickListener(::onBirthTimeClicked)



        return binding?.root
    }

    //erase the add button from the menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }


    private fun onAddStudentClicked(view: View){
        val student = Student(
            name=binding?.NameEditText?.text.toString(),
            id=binding?.IDEditText?.text.toString(),
            address = binding?.AddressEditText?.text.toString(),
            phone = binding?.PhoneEditText?.text.toString(),
            isChecked = binding?.checkBox?.isChecked ?: false,
            BirthDate = binding?.BirthDateEditText?.text.toString(),
            BirthTime = binding?.BirthTimeEditText?.text.toString()
        )
        binding?.progressBar?.visibility=View.VISIBLE

        if(didSetprofileImage) {

            binding?.StudentPic?.isDrawingCacheEnabled = true
            binding?.StudentPic?.buildDrawingCache()
            val bitmap = (binding?.StudentPic?.drawable as BitmapDrawable).bitmap

            Model.shared.addStudent(student, bitmap, Model.Storage.CLOUDINARY) {
                binding?.progressBar?.visibility = View.GONE
                Log.d("TAG", "Student added successfully!")
            }

        }else{
            Model.shared.addStudent(student, null, Model.Storage.CLOUDINARY) {
                binding?.progressBar?.visibility = View.GONE
                Log.d("TAG", "Student added successfully!")
            }
        }
        AlertDialog.Builder(requireContext())
            .setTitle("Saved")
            .setMessage("Student added successfully!")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                parentFragmentManager.popBackStack()
            }
            .show()
    }

    private fun onBirthDateClicked(view: View){
        DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${month + 1}/$year"
                binding?.BirthDateEditText?.setText(selectedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }
    private fun onBirthTimeClicked(view: View){
        TimePickerDialog(
            requireContext(),
            { _, hourOfDay, minute ->
                val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                binding?.BirthTimeEditText?.setText(selectedTime)
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true // 24-hour format
        ).show()
    }

}
