package akamal.de.lastfmappsfactory.platform.bases

import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.annotations.NotNull

abstract class BaseRvAdapter<T, V: RecyclerView.ViewHolder>(diffCallback: DiffUtil.ItemCallback<T>): ListAdapter<T, V>(diffCallback) {

    private var itemClickListener:RecyclerViewClickListener? = null

    fun setRecyclerViewClickListener(@NotNull itemClickListener: RecyclerViewClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onBindViewHolder(viewHolder: V, position: Int) {
        itemClickListener.let {
            viewHolder.itemView.setOnClickListener { itemClickListener?.itemClickListener(getItem(position)) }
        }
    }

}

interface RecyclerViewClickListener {
    fun<T>itemClickListener(selectedData: T)
}