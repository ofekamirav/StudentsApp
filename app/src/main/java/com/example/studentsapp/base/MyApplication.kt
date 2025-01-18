package com.example.studentsapp.base

import android.app.Application
import android.content.Context
import com.squareup.picasso.Picasso


//סט מכל מקום באפליקציה ככה ניתן לגשת לקונטק
class MyApplication: Application() {
    object Globals {
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        Globals.context = applicationContext


        Picasso.setSingletonInstance(Picasso.Builder(this).build())
    }
}