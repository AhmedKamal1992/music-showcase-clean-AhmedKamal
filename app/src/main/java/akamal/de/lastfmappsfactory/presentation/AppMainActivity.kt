package akamal.de.lastfmappsfactory.presentation

import akamal.de.lastfmappsfactory.R
import akamal.de.lastfmappsfactory.databinding.ActivityAppMainBinding
import akamal.de.lastfmappsfactory.platform.bases.BaseActivity
import akamal.de.lastfmappsfactory.presentation.favorites.FavoritesFragment
import akamal.de.lastfmappsfactory.presentation.topAlbums.TopAlbumsFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class AppMainActivity : BaseActivity<ActivityAppMainBinding>() {
    override fun layoutId(): Int = R.layout.activity_app_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        replaceFragment(FavoritesFragment())
        binding.get()?.view = this
    }

    fun backState(enable: Boolean) {
        binding.get()?.btnBack?.visibility = if(enable) View.VISIBLE else View.GONE
    }

    fun setTitle(title: String) {
        binding.get()?.textView5?.text = title
    }
}

