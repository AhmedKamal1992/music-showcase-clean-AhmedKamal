package akamal.de.lastfmappsfactory.presentation

import akamal.de.lastfmappsfactory.R
import akamal.de.lastfmappsfactory.databinding.ActivityAppMainBinding
import akamal.de.lastfmappsfactory.platform.bases.BaseActivity
import akamal.de.lastfmappsfactory.presentation.topAlbums.TopAlbumsFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AppMainActivity : BaseActivity<ActivityAppMainBinding>() {
    override fun layoutId(): Int = R.layout.activity_app_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        replaceFragment(TopAlbumsFragment())
        binding.get()?.let {
            it.view = this
            it.btnFav.setOnClickListener {  }
        }
    }
}

