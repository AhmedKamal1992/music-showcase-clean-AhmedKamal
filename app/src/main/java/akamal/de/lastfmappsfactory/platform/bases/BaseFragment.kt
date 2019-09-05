package akamal.de.lastfmappsfactory.platform.bases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection
import java.lang.ref.WeakReference

abstract class BaseFragment<B: ViewDataBinding>: Fragment() {

    @LayoutRes abstract fun layoutId(): Int
    lateinit var binding: WeakReference<B>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = WeakReference(DataBindingUtil.inflate(layoutInflater, layoutId(), container, false))
        return binding.get()?.root
    }

    fun showToast(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
    }
}