import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cst.cstacademy2024.R
import com.cst.cstacademy2024.models.Animal
import np.com.bimalkafle.cstacademy2024.viewmodels.animalViewModel

class FragmentOne : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AnimalListAdapter
    private lateinit var nameEditText: EditText
    private lateinit var continentEditText: EditText
    private lateinit var addButton: Button
    private lateinit var animalVM: animalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_one, container, false)

        recyclerView = view.findViewById(R.id.animal_list_recycler_view)
        nameEditText = view.findViewById(R.id.edit_text_name)
        continentEditText = view.findViewById(R.id.edit_text_continent)
        addButton = view.findViewById(R.id.add_button)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animalVM = ViewModelProvider(this).get(animalViewModel::class.java)

        // Initialize the adapter
        adapter = AnimalListAdapter()

        // Set the item click listener for the adapter
        adapter.setOnItemClickListener(object : AnimalListAdapter.OnItemClickListener {
            override fun onDeleteClick(animal: Animal) {
                animalVM.deleteAnimal(animal.id)
            }

            override fun onItemClick(animal: Animal) {
                // Handle item click here if needed
            }
        })

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        animalVM.allAnimals.observe(viewLifecycleOwner, { animals ->
            animals?.let { adapter.updateList(it) }
        })

        // Button click listener
        addButton.setOnClickListener {
            val animalName = capitalizeEachWord(nameEditText.text.toString().trim())
            val continent = capitalizeEachWord(continentEditText.text.toString().trim())

            if (animalName.isEmpty() || continent.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            val animalExists = adapter.animals.any { it.name.equals(animalName, ignoreCase = true) }
            if (animalExists) {
                Toast.makeText(
                    requireContext(),
                    "Animal '$animalName' already exists",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // Define valid continents
            val validContinents = listOf(
                "Europe",
                "Africa",
                "Asia",
                "North America",
                "South America",
                "Australia",
                "Antarctic"
            )
            // Check if the entered continent is valid
            val continentExists = validContinents.any { it.equals(continent, ignoreCase = true) }
            if (!continentExists) {
                Toast.makeText(
                    requireContext(),
                    "Continent '$continent' doesn't exist",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // If both animal and continent are valid, proceed with adding the animal
            val newAnimal =
                Animal(name = animalName.capitalize(), continent = continent.capitalize())
            animalVM.addAnimal(newAnimal.name, newAnimal.continent)

            // Clear EditTexts
            nameEditText.text.clear()
            continentEditText.text.clear()
        }
    }
    // Utility function to capitalize each word in a string
    private fun capitalizeEachWord(input: String): String {
        return input.split(" ").map { it.capitalize() }.joinToString(" ")
    }
}
