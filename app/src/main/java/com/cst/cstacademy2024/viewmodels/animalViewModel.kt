package np.com.bimalkafle.cstacademy2024.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cst.cstacademy2024.MainApplication
import com.cst.cstacademy2024.models.Animal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class animalViewModel : ViewModel() {

    // Assuming MainApplication has a singleton access to the database similar to the Todo app
    private val animalDao = MainApplication.animalDatabase.getAnimalDao()

    // LiveData holding the list of all animals
    val allAnimals: LiveData<List<Animal>> = animalDao.getAllAnimals()

    // Function to add a new animal to the database
    fun addAnimal(animalName: String, continent: String) {
        viewModelScope.launch(Dispatchers.IO) {
            animalDao.insertAnimal(Animal(name = animalName, continent = continent))
        }
    }

    // Function to delete an animal by ID
    fun deleteAnimal(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            animalDao.deleteAnimal(id)
        }
    }

}