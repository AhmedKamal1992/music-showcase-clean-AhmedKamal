package akamal.de.lastfmappsfactory.presentation.topAlbums

import akamal.de.lastfmappsfactory.R
import akamal.de.lastfmappsfactory.data.topAlbums.model.TopAlbum
import akamal.de.lastfmappsfactory.databinding.ItemAlbumBinding
import akamal.de.lastfmappsfactory.platform.bases.BaseRvAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class TopAlbumsRvAdapter: BaseRvAdapter<TopAlbum, TopAlbumsRvAdapter.TopAlbumsViewHolder>(TopAlbumsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopAlbumsViewHolder = TopAlbumsViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_album, parent, false))

    override fun onBindViewHolder(viewHolder: TopAlbumsViewHolder, position: Int) {
        super.onBindViewHolder(viewHolder, position)
        getItem(position)?.let {
            with(viewHolder) {
                itemView.tag = it.mbid
                bind(it)
            }
        }
    }

    class TopAlbumsViewHolder(private val binding: ItemAlbumBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(topAlbum: TopAlbum) {
            with(binding) {
                viewModel = TopAlbumsRvViewModel(topAlbum)
                executePendingBindings()
            }
        }
    }
}

class TopAlbumsRvViewModel(album: TopAlbum):ViewModel() {
    val title = ObservableField(album.name)
    val image = ObservableField(album.image[0].imageUrl)
    val playCount = ObservableField(album.playcount)
}

class TopAlbumsDiffCallback: DiffUtil.ItemCallback<TopAlbum>() {
    override fun areItemsTheSame(oldItem: TopAlbum, newItem: TopAlbum): Boolean = oldItem.mbid == newItem.mbid
    override fun areContentsTheSame(oldItem: TopAlbum, newItem: TopAlbum): Boolean = oldItem == newItem

}