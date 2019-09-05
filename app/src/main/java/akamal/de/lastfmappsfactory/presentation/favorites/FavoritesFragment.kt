package akamal.de.lastfmappsfactory.presentation.favorites


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import akamal.de.lastfmappsfactory.R
import akamal.de.lastfmappsfactory.data.topAlbums.model.TopAlbum
import akamal.de.lastfmappsfactory.databinding.FragmentFavoritesBinding
import akamal.de.lastfmappsfactory.platform.bases.BaseFragment
import akamal.de.lastfmappsfactory.platform.bases.RecyclerViewClickListener
import akamal.de.lastfmappsfactory.platform.extension.viewModel
import akamal.de.lastfmappsfactory.presentation.AppMainActivity
import akamal.de.lastfmappsfactory.presentation.albumDetails.AlbumDetailsFragment
import akamal.de.lastfmappsfactory.presentation.topAlbums.TopAlbumsRvAdapter
import akamal.de.lastfmappsfactory.presentation.topAlbums.TopAlbumsViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>() {
    override fun layoutId(): Int = R.layout.fragment_favorites

    @Inject lateinit var adapter: TopAlbumsRvAdapter
    @Inject lateinit var factory: ViewModelProvider.Factory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel<FavoritesViewModel>(factory) {
            binding.get()?.apply {
                this@viewModel.getAllAlbums()
                viewModel = this@viewModel
                rvAlbumsList.adapter = adapter
                lifecycleOwner = this@FavoritesFragment
                adapter.setRecyclerViewClickListener(object: RecyclerViewClickListener {
                    override fun <T> itemClickListener(selectedData: T) { with(selectedData as TopAlbum) { selectedData.mbid?.let { (activity as AppMainActivity).replaceFragment(AlbumDetailsFragment.newInstance(it)) }
                        ?: run { showToast(getString(R.string.no_album)) } } }
                })
            }
        }
    }
}
