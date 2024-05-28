//import android.graphics.Color
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.core.content.ContextCompat
//import androidx.fragment.app.Fragment
//import com.cst.cstacademy2024.R
//import com.cst.cstacademy2024.models.Animal
//
//class FragmentTwo : Fragment() {
//    private lateinit var animal: Animal
//    private lateinit var nameTextView: TextView
//    private lateinit var continentTextView: TextView
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.animal_details, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        // Retrieve the animal data passed from FragmentOne
//        arguments?.let {
//            animal = Animal(
//                it.getString("animal_name") ?: "",
//                it.getString("animal_continent") ?: ""
//            )
//        }
//
//        // Update the UI with the animal details
//        nameTextView = view.findViewById(R.id.tv_animal_name)
//        continentTextView = view.findViewById(R.id.tv_animal_continent)
//
//        nameTextView.text = animal.name
//        continentTextView.text = animal.continent
//
//        // Set the background color of the layout based on the continent
//        val backgroundColor = getBackgroundColorForContinent(animal.continent)
//        view.setBackgroundColor(backgroundColor)
//
//        // Set text color based on continent
//        val textColor = getTextColorForContinent(animal.continent)
//        nameTextView.setTextColor(textColor)
//        continentTextView.setTextColor(textColor)
//    }
//
//    private fun getBackgroundColorForContinent(continent: String): Int {
//        // Provide a color for each continent
//        return when (continent) {
//            "Europe" -> ContextCompat.getColor(requireContext(), R.color.green)
//            "Africa" -> ContextCompat.getColor(requireContext(), R.color.yellow)
//            "Asia" -> ContextCompat.getColor(requireContext(), R.color.red)
//            "North America" -> ContextCompat.getColor(requireContext(), R.color.brown)
//            "South America" -> ContextCompat.getColor(requireContext(), R.color.orange)
//            "Australia" -> ContextCompat.getColor(requireContext(), R.color.purple)
//            "Antarctic" -> ContextCompat.getColor(requireContext(), R.color.blue)
//            else -> Color.WHITE
//        }
//    }
//
//    private fun getTextColorForContinent(continent: String): Int {
//        // Provide a text color for each continent
//        return when (continent) {
//            "Europe" -> ContextCompat.getColor(requireContext(), R.color.white)
//            "Africa" -> ContextCompat.getColor(requireContext(), R.color.black)
//            "Asia" -> ContextCompat.getColor(requireContext(), R.color.white)
//            "North America" -> ContextCompat.getColor(requireContext(), R.color.white)
//            "South America" -> ContextCompat.getColor(requireContext(), R.color.black)
//            "Australia" -> ContextCompat.getColor(requireContext(), R.color.white)
//            "Antarctic" -> ContextCompat.getColor(requireContext(), R.color.white)
//            else -> ContextCompat.getColor(requireContext(), R.color.white)
//        }
//    }
//}