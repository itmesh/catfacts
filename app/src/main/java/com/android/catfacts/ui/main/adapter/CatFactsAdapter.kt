import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.catfacts.R
import com.android.catfacts.databinding.CatFactsRecycleItemBinding

class CatFactsAdapter(
    private val facts: List<String>
) : RecyclerView.Adapter<CatFactsAdapter.CatFactsViewHolder>() {
    inner class CatFactsViewHolder(
        val catFactsRecyclerItem: CatFactsRecycleItemBinding
    ) : RecyclerView.ViewHolder(catFactsRecyclerItem.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatFactsViewHolder =
        CatFactsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.cat_facts_recycle_item,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = facts.size

    override fun onBindViewHolder(holder: CatFactsViewHolder, position: Int) {
        holder.catFactsRecyclerItem.factId = facts[position]
    }
}
