package akamal.de.lastfmappsfactory.presentation.topAlbums


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import akamal.de.lastfmappsfactory.R
import akamal.de.lastfmappsfactory.data.topAlbums.model.TopAlbum
import akamal.de.lastfmappsfactory.databinding.FragmentTopAlbumsBinding
import akamal.de.lastfmappsfactory.platform.bases.BaseFragment
import akamal.de.lastfmappsfactory.platform.bases.RecyclerViewClickListener
import akamal.de.lastfmappsfactory.platform.extension.hideSoftKeyboard
import akamal.de.lastfmappsfactory.platform.extension.viewModel
import akamal.de.lastfmappsfactory.presentation.AppMainActivity
import akamal.de.lastfmappsfactory.presentation.albumDetails.AlbumDetailsFragment
import akamal.de.lastfmappsfactory.presentation.favorites.FavoritesFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import javax.inject.Inject

class TopAlbumsFragment : BaseFragment<FragmentTopAlbumsBinding>() {
    override fun layoutId(): Int = R.layout.fragment_top_albums

    @Inject lateinit var adapter: TopAlbumsRvAdapter
    @Inject lateinit var factory: ViewModelProvider.Factory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppMainActivity).setTitle(getString(R.string.search_top))
        viewModel<TopAlbumsViewModel>(factory) {
            binding.get()?.apply {
                viewModel = this@viewModel
                btnSearch.setOnClickListener { this@viewModel.getAllAlbums(etSearch.text.toString()); activity?.hideSoftKeyboard() }
                rvAlbumsList.adapter = adapter
                lifecycleOwner = this@TopAlbumsFragment
                adapter.setRecyclerViewClickListener(object: RecyclerViewClickListener {
                    override fun <T> itemClickListener(selectedData: T) {
                        with(selectedData as TopAlbum) {
                            selectedData.mbid?.let { (activity as AppMainActivity).replaceFragment(AlbumDetailsFragment.newInstance(it)) } ?: run { showToast(getString(R.string.no_album)) }
                        }
                    }
                })
            }
        }
    }
}
