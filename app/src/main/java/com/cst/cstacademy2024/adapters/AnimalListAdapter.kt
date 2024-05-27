import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.cst.cstacademy2024.R
import com.cst.cstacademy2024.models.Animal

class AnimalListAdapter(private val animals: MutableList<Animal>) :
    RecyclerView.Adapter<AnimalListAdapter.AnimalViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(animal: Animal)
    }

    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_animal, parent, false)
        return AnimalViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.bind(animals[position])
    }

    override fun getItemCount() = animals.size

    inner class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.tv_animal_name)
        private val continentTextView: TextView = itemView.findViewById(R.id.tv_animal_continent)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.delete_button)

        init {
            deleteButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    animals.removeAt(position)
                    notifyItemRemoved(position)
                }
            }

            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val animal = animals[position]
                    itemClickListener?.onItemClick(animal)
                }
            }
        }

        fun bind(animal: Animal) {
            nameTextView.text = animal.name
            continentTextView.text = animal.continent
        }
    }
}
