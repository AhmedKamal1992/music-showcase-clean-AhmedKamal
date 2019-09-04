package akamal.de.lastfmappsfactory.presentation.albumDetails


import android.os.Bundle
import androidx.fragment.app.Fragment

import akamal.de.lastfmappsfactory.R
import akamal.de.lastfmappsfactory.data.albumsDetails.model.TopAlbumDetails
import akamal.de.lastfmappsfactory.databinding.FragmentAlbumDetailsBinding
import akamal.de.lastfmappsfactory.platform.bases.BaseFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class AlbumDetailsFragment : BaseFragment<FragmentAlbumDetailsBinding>() {

    companion object {
        const val ARG_ALBUM_ID = "albumId"

        fun newInstance(albumId: String): AlbumDetailsFragment {
            val fragment = AlbumDetailsFragment()
            val args = Bundle()
            args.putString(ARG_ALBUM_ID, albumId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun layoutId(): Int = R.layout.fragment_album_details

    private lateinit var albumDetailsResponse: TopAlbumDetails
    @Inject lateinit var factory: ViewModelProvider.Factory
    @Inject lateinit var adapter:AlbumDetailsTracksRvAdapter

    override fun onStart() {
        super.onStart()
        binding.get()?.apply {
            isAlbumInFavorites = false
            val viewModel = ViewModelProviders.of(this@AlbumDetailsFragment, factory).get(AlbumDetailsViewModel::class.java)
            viewModel.albumDetailsMutable.observe(viewLifecycleOwner, Observer { album ->
                album?.let { albumDetailsResponse = album
                    albumDetails = album
                    isAlbumInFavorites = album.isFavorite
                    rvTracks.adapter = adapter
                    adapter.submitList(album.tracks.track)

                btnAddRemoveFavorites.setOnClickListener { viewModel.saveOrRemoveAlbum(albumDetailsResponse) } } })

            viewModel.isSavedInDbMutable.observe(viewLifecycleOwner, Observer { it?.let { isAlbumInFavorites = it; albumDetailsResponse.isFavorite = it; albumDetails = albumDetailsResponse } })

            arguments?.getString(ARG_ALBUM_ID)?.let {
                viewModel.getAlbumDetails(it)
            }
        }
    }
}
