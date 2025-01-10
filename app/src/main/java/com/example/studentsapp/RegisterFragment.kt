package com.example.studentsapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.studentsapp.databinding.RegisterFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterFragment : Fragment(){

    private var binding: RegisterFragmentBinding?= null
    private var auth: FirebaseAuth?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = RegisterFragmentBinding.inflate(inflater,container,false)

        auth = FirebaseAuth.getInstance()

        binding?.RegisterButton?.setOnClickListener(::RegisterUser)

        binding?.LoginButton?.setOnClickListener{
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            findNavController().navigate(action)
        }



        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun RegisterUser(view: View){
        val email=binding?.emailEditText?.text.toString().trim()
        val password= binding?.passwordEditText?.text.toString().trim()

        //validate email and password
        if(email.isEmpty() || password.isEmpty()){
            binding?.emailEditText?.error="Please enter email"
            binding?.passwordEditText?.error="Please enter password"
            return
        }
        if(password.length<6){
            binding?.passwordEditText?.error="Password must be at least 6 characters"
            return
        }
        else{
            binding?.passwordEditText?.error=null
            binding?.emailEditText?.error=null
        }

        // Register user in Firebase
        auth?.createUserWithEmailAndPassword(email, password)
            ?.addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    // Registration successful, Update UI with the signed-in user's information
                    Log.d("TAG", "createUserWithEmail:success")
                    val user = auth?.currentUser
                    UpdateUI(user)
                }else{
                    //if sign in fails, display a message to the user
                    Log.w("TAG", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        requireContext(),
                        "Authentication failed",
                        Toast.LENGTH_SHORT,
                    ).show()
                    UpdateUI(null)
                }
            }
    }

    private fun UpdateUI(user: FirebaseUser?){
        if (user != null) {
            Toast.makeText(
                requireContext(),
                "Welcome, ${user.email}",
                Toast.LENGTH_SHORT
            ).show()
            val mainActivityNavController = requireActivity().findNavController(R.id.main_nav_host)
            mainActivityNavController.navigate(R.id.studentsListfragment)
        } else {
            // Clear the input fields
            binding?.emailEditText?.text?.clear()
            binding?.passwordEditText?.text?.clear()
        }

    }

}