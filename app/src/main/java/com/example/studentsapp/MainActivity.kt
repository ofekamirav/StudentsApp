package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController

class MainActivity: AppCompatActivity() {

    var navController: NavController? = null
    var navHostFragment: NavHostFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //set up the toolbar
        val toolbar: Toolbar? = findViewById(R.id.main_toolbar)
        setSupportActionBar(toolbar)

        //set up the navigation controller
        navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host) as? NavHostFragment
        navController = navHostFragment?.navController
        navController?.let { NavigationUI.setupActionBarWithNavController(this, it) }



    }

    //Create the menu in toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //create inflator for view in xml
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //אם אני לא על דף הבית אז יעשה חזור אליו בעצם מטפל בחזור מהTOOLBAR
    //כל האקשנים מגיעים לפונקציה הזאת
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> navController?.popBackStack()
            else -> navController?.let{NavigationUI.onNavDestinationSelected(item, it)}//works for all fragments
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController?.navigateUp() ?: super.onSupportNavigateUp()
    }


}