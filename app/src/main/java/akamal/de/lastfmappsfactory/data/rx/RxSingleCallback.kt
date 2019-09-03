package akamal.de.lastfmappsfactory.data.rx

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RxSingleCallback {
    companion object {
        fun <T> getSchedulersForSingle(): (Single<T>) -> Single<T> {
            return { single: Single<T> ->
                single.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }
    }
}

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