package com.example.studentsapp.model;

import android.graphics.Bitmap
import android.os.Looper
import androidx.core.os.HandlerCompat
import com.example.studentsapp.base.EmptyCallback
import com.example.studentsapp.base.StringCallback
import com.example.studentsapp.base.StudentCallback
import com.example.studentsapp.model.dao.AppLocalDB
import com.example.studentsapp.model.dao.AppLocalDBRepository
import java.util.concurrent.Executors


class Model private constructor() {

    //Tell the server where to store the images
    enum class Storage{
        FIREBASE,
        CLOUDINARY
    }
    private val firebaseModel = FirebaseModel()
    private val cloudinaryModel = CloudinaryModel()

    companion object {
        val shared = Model()
    }

    fun getAllStudents(callback:StudentCallback) {
        firebaseModel.getAllStudents(callback)
    }

    fun addStudent(student: Student, image: Bitmap?, storage: Storage, callback: EmptyCallback) {
        firebaseModel.addStudent(student){
            image?.let {
                uploadTo(
                    storage = storage,
                    image = image,
                    name = student.id,
                    callback = { uri ->
                        if (!uri.isNullOrBlank()) {
                            val st = student.copy(avatarUrl = uri)
                            firebaseModel.addStudent(st, callback)
                        } else {
                            callback()
                        }
                    },
                )
            } ?: run{
                firebaseModel.addStudent(student, callback)
            }
        }
    }

    private fun uploadTo(storage: Storage, image: Bitmap, name: String, callback: (String?) -> Unit) {
        when (storage) {
            Storage.FIREBASE -> {
                uploadImageToFirebase(image, name, callback)
            }
            Storage.CLOUDINARY -> {
                uploadImageToCloudinary(
                    image = image,
                    name = name,
                    onSuccess = callback,
                    onError = { callback(null) }
                )
            }
        }
    }

    fun deleteStudent(student: Student, callback: EmptyCallback) {
        firebaseModel.deleteStudent(student, callback)

    }
    fun getStudentById(id: String, callback: StudentCallback) {
        firebaseModel.getStudentById(id, callback)

    }

    fun updateStudent(student: Student, callback: EmptyCallback) {
        firebaseModel.updateStudent(student, callback)

    }
    private fun uploadImageToFirebase(image: Bitmap, name: String, callback: StringCallback ) {
        firebaseModel.uploadImage(image, name, callback)
    }

    private fun uploadImageToCloudinary(image: Bitmap, name: String, onSuccess: StringCallback, onError: StringCallback) {
        cloudinaryModel.uploadImage(image, name, onSuccess, onError)

    }

}
