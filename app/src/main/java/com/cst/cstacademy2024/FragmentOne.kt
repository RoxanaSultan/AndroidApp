import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cst.cstacademy2024.R
import com.cst.cstacademy2024.models.Animal

class FragmentOne : Fragment() {
    private val animals = listOf(
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
        Animal("Emperor Penguin", "Antarctic"),
        Animal("Weddell Seal", "Antarctic"),
        Animal("Wandering Albatross", "Antarctic"),
        Animal("Leopard Seal", "Antarctic"),
        Animal("Killer Whale", "Antarctic"),
        Animal("Polar Bear", "Antarctic"),
        Animal("Adélie Penguin", "Antarctic"),
        Animal("South Polar Skua", "Antarctic"),
        Animal("Antarctic Petrel", "Antarctic"),
        Animal("Chinstrap Penguin", "Antarctic"),
        Animal("King Penguin", "Antarctic"),
        Animal("Gentoo Penguin", "Antarctic"),
        Animal("Southern Elephant Seal", "Antarctic"),
        Animal("Blue Whale", "Antarctic"),
        Animal("Antarctic Krill", "Antarctic"),
        Animal("Hourglass Dolphin", "Antarctic"),
        Animal("Emperor Penguin", "Antarctic"),
        Animal("Ross Seal", "Antarctic"),
        Animal("Jaguar", "South America"),
        Animal("African Elephant", "Africa"),
    ).shuffled()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AnimalListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.animal_list_recycler_view)

        // Initialize the adapter
        adapter = AnimalListAdapter(animals)

        // Set the item click listener for the adapter
        adapter.setOnItemClickListener(object : AnimalListAdapter.OnItemClickListener {
            override fun onItemClick(animal: Animal) {
                // Handle item click here
                val bundle = Bundle().apply {
                    putString("animal_name", animal.name)
                    putString("animal_continent", animal.continent)
                }
                val animalDetailFragment = FragmentTwo().apply {
                    arguments = bundle
                }
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, animalDetailFragment)
                    .addToBackStack(null)
                    .commit()
            }
        })

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }
}