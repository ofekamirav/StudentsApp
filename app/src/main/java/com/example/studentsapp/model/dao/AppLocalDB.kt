package com.example.studentsapp.model.dao

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.studentsapp.base.MyApplication
import com.example.studentsapp.model.Student

@Database(entities = [Student::class], version = 2)
abstract class AppLocalDBRepository : RoomDatabase() {
    abstract fun studentDao(): StudentDao

}
class AppLocalDB {

    val database: AppLocalDBRepository by lazy {

        val context = MyApplication.Globals.context ?: throw IllegalStateException("Context not initialized")

            Room.databaseBuilder(
                context= context,
                klass = AppLocalDBRepository::class.java,
                name = "dbFileName.db"
            )
                .fallbackToDestructiveMigration()//hundler the versions
                .build()

    }
}