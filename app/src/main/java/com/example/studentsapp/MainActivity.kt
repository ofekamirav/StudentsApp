package com.example.studentsapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.studentsapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity: AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    var navController: NavController? = null
    var navHostFragment: NavHostFragment? = null
    internal var currentStudentId: String? = null
    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        auth = FirebaseAuth.getInstance()

        //set up the toolbar
        setSupportActionBar(binding?.mainToolbar)

        //set up the navigation controller
        navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host) as? NavHostFragment
        navController = navHostFragment?.navController
        navController?.let { NavigationUI.setupActionBarWithNavController(this, it) }

        navController?.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.loginFragment || destination.id == R.id.registerFragment) {
                hideToolbar()
            } else {
                showToolbar()
            }
        }

        // check if the user is logged in
        val currentUser = auth?.currentUser
        if (currentUser != null) {
            // if user is logged in, navigate to the students list fragment
            navController?.navigate(R.id.studentsListfragment)
        } else {
            // if user is not logged in, navigate to the login fragment
            navController?.navigate(R.id.loginFragment)
        }

    }

    //Create the menu in toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //create inflator for view in xml
        menuInflater.inflate(R.menu.addstudent_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun hideToolbar() {
        binding?.mainToolbar?.visibility = View.GONE
    }
    private fun showToolbar() {
        binding?.mainToolbar?.visibility = View.VISIBLE
    }

    //אם אני לא על דף הבית אז יעשה חזור אליו בעצם מטפל בחזור מהTOOLBAR
    //כל האקשנים מגיעים לפונקציה הזאת
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> navController?.popBackStack()
            R.id.LogoutButton -> {
                // ביצוע logout מ-Firebase
                auth?.signOut()
                navController?.navigate(R.id.loginFragment)
            }
            R.id.editStudentFragment -> {
                val bundle = Bundle().apply {
                    putString("studentId", currentStudentId)
                }
                navController?.navigate(R.id.action_global_editStudentFragment, bundle)
            }
            else -> navController?.let { NavigationUI.onNavDestinationSelected(item, it) }
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController?.navigateUp() ?: super.onSupportNavigateUp()
    }




}