package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity.RESULT_OK
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.studentsapp.model.Model
import com.google.android.material.checkbox.MaterialCheckBox

class StudentDetailsFragment : Fragment() {

    var nameValue: TextView?= null
    var IdValue: TextView?= null
    var phoneValue: TextView?= null
    var addressValue: TextView?= null
    var checkedBox: MaterialCheckBox?= null
    var backButton: Button?= null
    var editStudentBtn: Button?= null
    var deleteStudentBtn: Button?= null

    //get the data from the bundle
    private var name: String? = null
    private var id: String? = null
    private var phone: String? = null
    private var address: String? = null
    private var isChecked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args=StudentDetailsFragmentArgs.fromBundle(requireArguments())
        name=args.studentName
        id=args.studentId
        phone=args.studentPhone
        address=args.studentAddress
        isChecked=args.isChecked
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.student_details, container, false)

        setUp(view)

        backButton?.setOnClickListener{
            Navigation.findNavController(view).popBackStack()
        }

        editStudentBtn?.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_studentDetailsFragment_to_editStudentFragment)
        }

        return view
    }

    private fun setUp(view: View?){
        //get the fields from the layout
        nameValue = view?.findViewById(R.id.nameValue)
        IdValue = view?.findViewById(R.id.IdValue)
        phoneValue = view?.findViewById(R.id.phoneValue)
        addressValue = view?.findViewById(R.id.addressValue)
        checkedBox = view?.findViewById(R.id.checkedBox)

        //get the buttons from the layout
        backButton = view?.findViewById(R.id.backButton)
        editStudentBtn = view?.findViewById(R.id.EditStudentBtn)
        deleteStudentBtn = view?.findViewById(R.id.DeleteStudentBtn)

        //set the values of the fields
        nameValue?.text = name
        IdValue?.text = id
        phoneValue?.text = phone
        addressValue?.text = address
        checkedBox?.isChecked = isChecked


    }

//
//    //set the click listeners for the buttons
//    backButton.setOnClickListener {
//        finish()
//    }
//    deleteStudentBtn.setOnClickListener {
//        val studentId=id
//        if(studentId!=null){
//            Model.shared.deleteStudent(studentId)
//            finish()
//        }
//    }
//
//    editStudentBtn.setOnClickListener {
//        val intent= Intent(this,EditStudent::class.java)
//
//        //send the data to EditStudentActivity
//        intent.putExtra("name", name)
//        intent.putExtra("id", id)
//        intent.putExtra("phone", phone)
//        intent.putExtra("address", address)
//        intent.putExtra("isChecked", isChecked)
//
//        startActivityForResult(intent, 100)
//    }
//
//}
//
//override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//    super.onActivityResult(requestCode, resultCode, data)
//
//    if (requestCode == 100 && resultCode == RESULT_OK) {
//        //get the update data after edit student
//        val updatedName = data?.getStringExtra("student_name")
//        val updatedPhone = data?.getStringExtra("student_phone")
//        val updatedAddress = data?.getStringExtra("student_address")
//        val updatedIsChecked = data?.getBooleanExtra("student_isChecked", false)
//
//        //update the fields with the new data
//        nameValue?.text = updatedName
//        phoneValue?.text = updatedPhone
//        addressValue?.text = updatedAddress
//        checkedBox?.isChecked = updatedIsChecked ?: false
//    }
//}





}