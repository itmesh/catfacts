import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.catfacts.R
import com.android.catfacts.databinding.CatFactsRecycleItemBinding
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation

class CatFactsAdapter(
    private val facts: List<String>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<CatFactsAdapter.CatFactsViewHolder>() {
    inner class CatFactsViewHolder(
        val catFactsRecyclerItem: CatFactsRecycleItemBinding
    ) : RecyclerView.ViewHolder(catFactsRecyclerItem.root) {
        private val picasso: Picasso = Picasso.get()

        fun loadImage(url: String) {
            picasso.load(url).transform(RoundedCornersTransformation(15, 15))
                .placeholder(R.drawable.cat_logo).into(catFactsRecyclerItem.imageView)
        }
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
        holder.loadImage("https://i.imgur.com/kUCMPWX.jpg")
        holder.itemView.setOnClickListener { onClick(facts[position]) }
    }
}
