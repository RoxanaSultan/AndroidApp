import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cst.cstacademy2024.R
import com.cst.cstacademy2024.models.Animal

class FragmentOne : Fragment() {
    private val animals = mutableListOf(
        Animal("European Wolf", "Europe"),
        Animal("Brown Bear", "Europe"),
        Animal("Red Fox", "Europe"),
        Animal("European Bison", "Europe"),
        Animal("Moose", "Europe"),
        Animal("African Elephant", "Africa"),
        Animal("Lion", "Africa"),
        Animal("Giraffe", "Africa"),
        Animal("Zebra", "Africa"),
        Animal("Hippopotamus", "Africa"),
        Animal("Bengal Tiger", "Asia"),
        Animal("Giant Panda", "Asia"),
        Animal("Snow Leopard", "Asia"),
        Animal("Red Panda", "Asia"),
        Animal("Asian Elephant", "Asia"),
        Animal("Grizzly Bear", "North America"),
        Animal("Bald Eagle", "North America"),
        Animal("American Bison", "North America"),
        Animal("Mountain Lion", "North America"),
        Animal("Gray Wolf", "North America"),
        Animal("Jaguar", "South America"),
        Animal("Anaconda", "South America"),
        Animal("Capuchin Monkey", "South America"),
        Animal("Sloth", "South America"),
        Animal("Macaw", "South America"),
        Animal("Kangaroo", "Australia"),
        Animal("Koala", "Australia"),
        Animal("Emu", "Australia"),
        Animal("Platypus", "Australia"),
        Animal("Tasmanian Devil", "Australia"),
        Animal("Emperor Penguin", "Antarctica"),
        Animal("Weddell Seal", "Antarctica"),
        Animal("Wandering Albatross", "Antarctica"),
        Animal("Leopard Seal", "Antarctica"),
        Animal("Killer Whale", "Antarctica"),
        Animal("Polar Bear", "Antarctica"),
        Animal("Adélie Penguin", "Antarctica"),
        Animal("South Polar Skua", "Antarctica"),
        Animal("Antarctic Petrel", "Antarctica"),
        Animal("Chinstrap Penguin", "Antarctica"),
        Animal("King Penguin", "Antarctica"),
        Animal("Gentoo Penguin", "Antarctica"),
        Animal("Southern Elephant Seal", "Antarctica"),
        Animal("Blue Whale", "Antarctica"),
        Animal("Antarctic Krill", "Antarctica"),
        Animal("Hourglass Dolphin", "Antarctica"),
        Animal("Emperor Penguin", "Antarctica"),
        Animal("Ross Seal", "Antarctica"),
        Animal("Jaguar", "South America"),
        Animal("African Elephant", "Africa")
    )

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AnimalListAdapter
    private lateinit var nameEditText: EditText
    private lateinit var continentEditText: EditText
    private lateinit var addButton: Button
    private lateinit var deleteButton: ImageButton

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

        // Initialize the adapter
        adapter = AnimalListAdapter(animals)

        // Set the item click listener for the adapter
//        adapter.setOnItemClickListener(object : AnimalListAdapter.OnItemClickListener {
//            override fun onItemClick(animal: Animal) {
//                // Handle item click here
//                val bundle = Bundle().apply {
//                    putString("animal_name", animal.name)
//                    putString("animal_continent", animal.continent)
//                }
//                val animalDetailFragment = FragmentTwo().apply {
//                    arguments = bundle
//                }
//                requireActivity().supportFragmentManager.beginTransaction()
//                    .replace(R.id.fragment_container, animalDetailFragment)
//                    .addToBackStack(null)
//                    .commit()
//            }
//        })

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        // Button click listener
        addButton.setOnClickListener {
            val animalName = nameEditText.text.toString().trim()
            val continent = continentEditText.text.toString().trim()

            if (animalName.isEmpty() || continent.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val continentExists = animals.any { it.continent.equals(continent, ignoreCase = true) }
            if (!continentExists) {
                Toast.makeText(requireContext(), "Continent '$continent' doesn't exist", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val animalExists = animals.any { it.name.equals(animalName, ignoreCase = true) }
            if (animalExists) {
                Toast.makeText(requireContext(), "Animal '$animalName' already exists", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newAnimal = Animal(animalName.capitalize(), continent.capitalize())
            animals.add(newAnimal)
            adapter.notifyDataSetChanged()

            // Clear EditTexts
            nameEditText.text.clear()
            continentEditText.text.clear()
        }

    }
}
