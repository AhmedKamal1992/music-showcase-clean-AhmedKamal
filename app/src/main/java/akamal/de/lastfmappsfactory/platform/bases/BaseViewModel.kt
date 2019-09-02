package akamal.de.lastfmappsfactory.platform.bases

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel(application: Application): AndroidViewModel(application) {
    val toastMutable: MutableLiveData<String> = MutableLiveData()
    val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        compositeDisposable.dispose()
    }
}