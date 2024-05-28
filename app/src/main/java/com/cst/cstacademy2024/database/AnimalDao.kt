package com.cst.cstacademy2024.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cst.cstacademy2024.models.Animal

@Dao
interface AnimalDao {
    @Query("SELECT * FROM animal")
    fun getAllAnimals(): LiveData<List<Animal>>
    @Insert
    fun insertAnimal(animal: Animal)
    @Query("DELETE FROM animal WHERE id = :id")
    fun deleteAnimal(id: Int)
}