package akamal.de.lastfmappsfactory.presentation.albumDetails

import akamal.de.lastfmappsfactory.R
import akamal.de.lastfmappsfactory.data.albumsDetails.model.AlbumTracks
import akamal.de.lastfmappsfactory.databinding.ItemTracksBinding
import akamal.de.lastfmappsfactory.platform.bases.BaseRvAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class AlbumDetailsTracksRvAdapter: BaseRvAdapter<AlbumTracks, AlbumDetailsTracksRvAdapter.TracksViewHolder>(TracksDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TracksViewHolder {
        return TracksViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_tracks, parent, false))
    }

    override fun onBindViewHolder(viewHolder: TracksViewHolder, position: Int) {
        getItem(position)?.let {
            with(viewHolder) {
                itemView.tag = it.url
                bind(it)
            }
        }
    }

    class TracksViewHolder(private val binding: ItemTracksBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(singleTrack: AlbumTracks) {
            with(binding) {
                track = TracksRvViewModel(singleTrack)
                executePendingBindings()
            }
        }
    }

    class TracksRvViewModel(singleTrack: AlbumTracks):ViewModel() {
        val trackName = ObservableField(singleTrack.name)
        val duration = ObservableField(singleTrack.durationDivided)
    }

    class TracksDiffCallback: DiffUtil.ItemCallback<AlbumTracks>() {
        override fun areItemsTheSame(oldItem: AlbumTracks, newItem: AlbumTracks): Boolean = oldItem.url == newItem.url
        override fun areContentsTheSame(oldItem: AlbumTracks, newItem: AlbumTracks): Boolean = oldItem == newItem

    }
}