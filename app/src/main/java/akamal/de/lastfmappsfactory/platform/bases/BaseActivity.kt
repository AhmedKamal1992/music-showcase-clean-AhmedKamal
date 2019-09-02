package akamal.de.lastfmappsfactory.platform.bases

import akamal.de.lastfmappsfactory.R
import akamal.de.lastfmappsfactory.platform.extension.inTransaction
import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import java.lang.ref.WeakReference

abstract class BaseActivity<B: ViewDataBinding>: AppCompatActivity() {

    @LayoutRes abstract fun layoutId(): Int
    lateinit var binding: WeakReference<B>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WeakReference(DataBindingUtil.setContentView(this, layoutId()))
    }

    fun <B: ViewDataBinding> replaceFragment(fragment: BaseFragment<B>) = supportFragmentManager.inTransaction { replace(
        R.id.app_main_container, fragment) }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }


}