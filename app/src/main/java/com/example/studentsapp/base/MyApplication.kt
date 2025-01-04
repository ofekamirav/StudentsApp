package com.example.studentsapp.base

import android.app.Application
import android.content.Context


//סט מכל מקום באפליקציה ככה ניתן לגשת לקונטק
class MyApplication: Application() {
    object Globals {
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        Globals.context = applicationContext
    }
}