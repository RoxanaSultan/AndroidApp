package com.cst.cstacademy2024.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Animal(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val continent: String
)
