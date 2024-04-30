import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cst.cstacademy2024.R
import com.cst.cstacademy2024.models.Animal

class AnimalListAdapter(private val animals: List<Animal>) :
    RecyclerView.Adapter<AnimalListAdapter.AnimalViewHolder>() {

    // Define the interface for item click listener
    interface OnItemClickListener {
        fun onItemClick(animal: Animal)
    }

    // Declare a variable to hold the click listener
    private var itemClickListener: OnItemClickListener? = null

    // Function to set the item click listener from outside
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_animal, parent, false)
        return AnimalViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.bind(animals[position])
    }

    override fun getItemCount() = animals.size

    inner class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.tv_animal_name)
        private val continentTextView: TextView = itemView.findViewById(R.id.tv_animal_continent)

        fun bind(animal: Animal) {
            val layoutResourceId = getLayoutResourceForContinent(animal.continent)
            val inflater = LayoutInflater.from(itemView.context)
            val layout = inflater.inflate(layoutResourceId, null) as LinearLayout

            // Find text views in the inflated layout
            val nameTextView: TextView = layout.findViewById(R.id.tv_animal_name)
            val continentTextView: TextView = layout.findViewById(R.id.tv_animal_continent)

            // Set text values
            nameTextView.text = animal.name
            continentTextView.text = animal.continent

            // Remove all views from the item's LinearLayout and add the inflated layout
            (itemView as LinearLayout).removeAllViews()
            (itemView as LinearLayout).addView(layout)
        }


        private fun getLayoutResourceForContinent(continent: String): Int {
            return when (continent) {
                "Europe" -> R.layout.item_animal_europe
                "Africa" -> R.layout.item_animal_africa
                "Asia" -> R.layout.item_animal_asia
                "North America" -> R.layout.item_animal_north_america
                "South America" -> R.layout.item_animal_south_america
                "Australia" -> R.layout.item_animal_australia
                "Antarctic" -> R.layout.item_animal_antarctica
                else -> R.layout.item_animal_europe
            }
        }
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val animal = animals[position]
                    itemClickListener?.onItemClick(animal) // Call onItemClick on the itemClickListener instance
                }
            }
        }
    }
}
