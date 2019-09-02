package akamal.de.lastfmappsfactory.platform.extension

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*

inline fun <reified T: ViewModel> FragmentActivity.getViewModel(viewModelFactory: ViewModelProvider.Factory): T
        = ViewModelProviders.of(this, viewModelFactory)[T::class.java]

inline fun <reified T: ViewModel> FragmentActivity.viewModel(viewModelFactory: ViewModelProvider.Factory, body: T.() -> Unit): T {
    val vm = getViewModel<T>(viewModelFactory)
    vm.body()
    return vm
}

fun <T: Any, L: LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) {
    liveData.observe(this, Observer(body))
}