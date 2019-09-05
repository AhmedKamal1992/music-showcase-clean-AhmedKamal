package akamal.de.lastfmappsfactory.data.rx

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RxObservableCallback {
    companion object {
        fun <T> getSchedulersForObservable(): (Observable<T>) -> Observable<T> {
            return { observable: Observable<T> ->
                observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }
    }
}