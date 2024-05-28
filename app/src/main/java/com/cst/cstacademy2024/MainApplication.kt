package com.cst.cstacademy2024

import android.app.Application
import androidx.room.Room
import com.cst.cstacademy2024.database.AnimalDatabase

class MainApplication : Application() {
    companion object {
        lateinit var animalDatabase : AnimalDatabase
    }

    override fun onCreate() {
        super.onCreate()
        animalDatabase = Room.databaseBuilder(applicationContext, AnimalDatabase::class.java, AnimalDatabase.DATABASE_NAME).build()
    }
}